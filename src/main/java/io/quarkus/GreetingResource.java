package io.quarkus;

import java.nio.charset.StandardCharsets;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

    public static final String BASIC_STRING = "123456789asdfghjkl3245ffffff23t4rg4rt45zrt57h5g4jhg54kk4jj5k4hj3lk45jh4l5hjk4öl5kjl4ö5jk3lö453lkä4lö";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello jaxrs";
    }

    @Path("getString")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getString(@QueryParam("times") @DefaultValue("1") Integer times) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(BASIC_STRING);
        }
        String finalString = sb.toString();
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Built string with {} chars. bytes: {}", finalString.length(), finalString.getBytes(StandardCharsets.UTF_8).length);

        logger.info("java.version: " + System.getProperty("java.version"));
        return finalString;
    }

    @Path("getString")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getStringJson(@QueryParam("times") @DefaultValue("1") Integer times) {
        return getString(times);
    }
}
