/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.server;

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
     * @param delaySecondsString
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String echo(@QueryParam("name") String name, 
                       @QueryParam("delaySeconds") Long delaySecondsString) {
        return "Hello " + name;
    }
}
