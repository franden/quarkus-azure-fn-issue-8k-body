package io.quarkus.issue.boundary;

import io.quarkus.issue.control.StringCreator;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/jaxrs")
public class GreetingResource {

    public static final String MEDIA_TYPE_TEXT_PLAIN = MediaType.TEXT_PLAIN + ";charset=utf-8";

    @Inject
    StringCreator stringCreator;

    @Produces(MEDIA_TYPE_TEXT_PLAIN)
    @GET
    public String getString(@QueryParam("times") @DefaultValue("1") Integer times) {
        return stringCreator.createLongString(times);
    }
}
