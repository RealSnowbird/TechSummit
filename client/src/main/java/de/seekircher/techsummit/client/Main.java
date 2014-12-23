/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.client;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author snowbird
 */
public class Main {
    private static final int NUM_ITERATIONS = 10;
    private static final int DELAY_TIME_SECS = 5;
    
    public static void main(String[] args) {
        DemoClient client = new DemoClient();
        Stopwatch stopwatch = new Stopwatch();
    
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            stopwatch.reset().start();
            String response = client.echo("Norbert", DELAY_TIME_SECS, true);
            
            System.out.println(String.format(
                    "Response after %d msecs: %s", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS), response));
        }
    }
}