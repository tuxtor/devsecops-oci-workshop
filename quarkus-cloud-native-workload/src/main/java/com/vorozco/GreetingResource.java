package com.vorozco;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var dummyVar = doSpotBugsDemo();
        return "Hello from Quarkus REST";
    }

    private String doSpotBugsDemo() {
        var dummyVar = "This is not an error";
        return "SpotBugs demo";
    }
}
