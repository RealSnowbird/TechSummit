/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.server;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author snowbird
 */
public class EchoCommand extends HystrixCommand<String>  {
    private final Integer delaySeconds;
    private final String name;
    
    private static final HystrixCommandGroupKey MY_HYSTRIX_COMMAND_GROUP_KEY = 
            HystrixCommandGroupKey.Factory.asKey("DemoCommandGroup");
    private static final int TIMEOUT = 3000;
    private static final int WINDOW_SIZE_MSECS = 60000; //Time window for circuit breaker metrics
    private static final int REQUEST_VOLUME_THRESHOLD = 3; //Requests to fail in time window until circuit is opened
    
    public EchoCommand(String name, Integer delaySeconds) {
        super(Setter.withGroupKey(MY_HYSTRIX_COMMAND_GROUP_KEY)
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                                .withCircuitBreakerEnabled(true)
                                .withCircuitBreakerRequestVolumeThreshold(REQUEST_VOLUME_THRESHOLD)
                                .withMetricsRollingStatisticalWindowInMilliseconds(WINDOW_SIZE_MSECS)
                )
        );
        this.delaySeconds = delaySeconds;
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if (delaySeconds != null) {
            System.out.println("Waiting for " + delaySeconds + " seconds");
            Thread.sleep(delaySeconds * 1000);
        }
           
        return "Hello " + name;
    }

    @Override
    protected String getFallback() {
        if (!isCircuitBreakerOpen()) {
            return "We are super sorry, but there was an error. Most probably caused by you, the user!";
        } else {
            return "YOU BROKE THE CIRCUIT YOU ****** *****!!!";
        }
    }
}
