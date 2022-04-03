package com.prudential.platform.routes;

import com.prudential.platform.model.entity.Document;
import com.prudential.platform.service.CouchbaseService;
import com.prudential.platform.util.ObjectMapperUtil;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/query")
@RequestScoped
//@Traced
public class CouchbaseRoutes {

    private static final Logger LOGGER = Logger.getLogger("CouchbaseRoutes");
    @Inject
    ObjectMapperUtil objectMapperUtil;
    @Context
    HttpServerRequest httpServerRequest;
    @Inject
    CouchbaseService couchBaseService;

    @GET
    @Path("getPmnDocTypes")
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getPmnDocTypes() throws Exception {

        Uni<Response> couchbaseResponse = null;
        try {
            couchbaseResponse = couchBaseService.getPmnDocTypes();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }

        return couchbaseResponse;

    }

    @GET
    @Path("getPmnDocument/{cbDocId}")
    @Blocking
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getPmnDocument(@PathParam("cbDocId") String cbDocId) throws Exception {
        LOGGER.info("pmndoc by cb doc id");
        LOGGER.info(cbDocId);
        Uni<Response> couchbaseResponse = null;
        try {
            couchbaseResponse = couchBaseService.getPmnDocument(cbDocId);
//            couchbaseResponse = Uni.createFrom().item(Response.ok(cbDocId, MediaType.APPLICATION_JSON_TYPE).build());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return couchbaseResponse;

    }

    @GET
    @Path("getPmnDocument/{caseId}/{type}")
    @Blocking
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getPmnDocument(@PathParam("caseId") String caseId, @PathParam("type") String type) throws Exception {
        LOGGER.info("pmndoc by case id");

        Uni<Response> couchbaseResponse = null;
        try {
//            couchbaseResponse = couchBaseService.getPmnDocTypes();
            couchbaseResponse = Uni.createFrom().item(Response.ok(type, MediaType.APPLICATION_JSON_TYPE).build());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return couchbaseResponse;

    }

    @GET
    @Path("getMetaData")
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getMetaData(@QueryParam("settlementId") String pmnSettlementId, @QueryParam("caseId") String caseId) throws Exception {

        Uni<Response> couchbaseResponse = null;
        try {
            // LOGGER.info(pmnSettlementId);
            // LOGGER.info(caseId);
            couchbaseResponse = couchBaseService.getMetaData(pmnSettlementId, caseId);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }

        return couchbaseResponse;

    }

    @GET
    @Path("getHnwi")
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getHnwi(@QueryParam("caseId") String caseId) throws Exception {

        Uni<Response> couchbaseResponse = null;
        try {
            // LOGGER.info(caseId);
            couchbaseResponse = couchBaseService.getHnwi(caseId);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }

        return couchbaseResponse;

    }

    @GET
    @Path("getLifeAssuredPolicyHolder")
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> getLifeAssuredPolicyHolder(@QueryParam("caseId") String caseId) throws Exception {

        Uni<Response> couchbaseResponse = null;
        try {
            // LOGGER.info(caseId);
            couchbaseResponse = couchBaseService.getLifeAssuredPolicyHolder(caseId);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }

        return couchbaseResponse;

    }

    @POST
    @Path("createDocument")
    //@RolesAllowed({"AGENT","agent"})
    public Uni<Response> createDocument(String json) throws Exception {

        Uni<Response> couchbaseResponse = null;
        Document document = new Document();
        try {
            document = (Document) objectMapperUtil.jsonToModel(json, Document.class);
            couchbaseResponse = couchBaseService.createDocument(document);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }

        return couchbaseResponse;

    }
}
