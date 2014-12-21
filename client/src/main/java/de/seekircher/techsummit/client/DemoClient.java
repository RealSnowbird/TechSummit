/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:DemoResource [demo]<br>
 * USAGE:
 * <pre>
 *        DemoClient client = new DemoClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author snowbird
 */
public class DemoClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/server/webresources";

    public DemoClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("demo");
    }

    public String echo(String name) throws ClientErrorException {
        return echo(0L, name);
    }
    
    public String echo(Long delaySeconds, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (delaySeconds != null) {
            resource = resource.queryParam("delaySeconds", delaySeconds);
        }
        if (name != null) {
            resource = resource.queryParam("name", name);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
