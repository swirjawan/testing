package com.prudential.platform.util;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseUtil {

    private static final Logger LOGGER = Logger.getLogger("ResponseUtil");

    public javax.ws.rs.core.Response onSucessResponse(Object buildObject) {

        //LOGGER.info("onSuccess");
        javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok().entity(buildObject);
        return responseBuilder.build();

    }

    public javax.ws.rs.core.Response onNoContentResponse() {

        //LOGGER.info("onSuccess");
        javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.noContent();
        return responseBuilder.build();

    }

}
