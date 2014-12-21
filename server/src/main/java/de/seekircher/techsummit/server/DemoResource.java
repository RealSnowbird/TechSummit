/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.server;

import javax.management.RuntimeErrorException;
import javax.naming.OperationNotSupportedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author snowbird
 */
@Path("demo")
public class DemoResource {
    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of de.seekircher.techsummit.server.DemoResource
     * @param name
     * @param delaySeconds
     * @param useHystrix
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    public String echo(@QueryParam("name") String name, 
                       @QueryParam("delaySeconds") Integer delaySeconds,
                       @QueryParam("useHystrix") Boolean useHystrix) throws Exception {
        
        if ((useHystrix != null) && useHystrix) {
            return echoHystrix(name, delaySeconds);
        } else {
            return echoPlain(name, delaySeconds);
        }
    }
    
    private String echoPlain(String name, Integer delaySeconds) throws InterruptedException {
        System.out.println("*** Executing echo without Hystrix ***");
        if (delaySeconds != null) {
            System.out.println("*** Waiting for " + delaySeconds + " seconds ***");
            Thread.sleep(delaySeconds * 1000);
        }
           
        return "Hello " + name;
    }
    
    private String echoHystrix(String name, Integer delaySeconds) {
        System.out.println("*** Executing echo with Hystrix ***");
        return new EchoCommand(name, delaySeconds).execute();
    }
}
