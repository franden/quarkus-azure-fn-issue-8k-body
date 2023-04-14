package io.quarkus;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello jaxrs";
    }

    @Path("getString")
    @GET
    public String getString(@QueryParam("times")@DefaultValue("1") Integer times) {
        String basicString = "123456789asdfghjkl3245ffffff23t4rg4rt45zrt57h5g4jhg54kk4jj5k4hj3lk45jh4l5hjk4öl5kjl4ö5jk3lö453lkä4lö";
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<times;i++ ) {
            sb.append(basicString);
        }
        String finalString = sb.toString();
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Built string with {} chars. bytes: {}",finalString.length(), finalString.getBytes(StandardCharsets.UTF_8).length);

        logger.info("java.version: " + System.getProperty("java.version"));
        return finalString;
    }
}
