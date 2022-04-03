package com.prudential.platform.service;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;
import com.prudential.platform.constant.QueryConstant;
import com.prudential.platform.dto.CouchbaseResponse;
import com.prudential.platform.dto.PmnDocTypes;
import com.prudential.platform.model.entity.Document;
import com.prudential.platform.util.CouchbaseUtil;
import com.prudential.platform.util.ObjectMapperUtil;
import com.prudential.platform.util.ResponseUtil;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@ApplicationScoped
public class CouchbaseService {

    private static final Logger LOGGER = Logger.getLogger("CouchbaseService");
    @ConfigProperty(name = "prudential.couchbase.bucket-name")
    public String bucketName;
    @Inject
    ObjectMapperUtil objectMapperUtil;
    @Inject
    ResponseUtil responseUtil;
    @Inject
    CouchbaseUtil cbUtil;

    public Uni<javax.ws.rs.core.Response> getPmnDocTypes() throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;
        PmnDocTypes pmnDocTypes = new PmnDocTypes();

        try {
            Cluster cluster = cbUtil.getCluster();
            QueryResult result = cluster.query(QueryConstant.GET_PMN_DOCTYPES);

            if (!result.rowsAsObject().isEmpty()) {
                JsonArray jArray = result.rowsAsObject().get(0).getArray("pmnDocTypes");

                String[] pmnDocTypeArrays = new String[jArray.size()];
                for (int i = 0; i < pmnDocTypeArrays.length; i++) {
                    pmnDocTypeArrays[i] = jArray.getString(i);
                }
                pmnDocTypes.setPmnDocTypes(pmnDocTypeArrays);
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onSucessResponse(pmnDocTypes));
            } else {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onNoContentResponse());
            }

        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }

    public Uni<javax.ws.rs.core.Response> getPmnDocument(String cbDocId) throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;

        try {
            Cluster cluster = cbUtil.getCluster();
            QueryResult result = cluster.query(QueryConstant.GET_PMN_DOC, queryOptions().parameters(JsonObject.create().put("key", cbDocId)));

            if (!result.rowsAsObject().isEmpty()) {
                LOGGER.info("data found");
                JsonObject resultData = result.rowsAsObject().get(0);//result always 1 since it was using Document ID
                couchbaseResponse = Uni.createFrom().item(Response.ok(resultData.toString(), MediaType.APPLICATION_JSON_TYPE).build());
            } else {
                LOGGER.info("data not found");
                couchbaseResponse = Uni.createFrom().item(Response.noContent().build());
            }

        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }

    public Uni<javax.ws.rs.core.Response> getMetaData(String pmnSettlementId, String caseId) throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;
        String query = "";
        JsonObject queryParameter;

        try {
            Cluster cluster = cbUtil.getCluster();

            if (pmnSettlementId != null && caseId != null) {
                query = QueryConstant.GET_METADATA_CLAIM_SETTLEMENT_DETAIL;
                queryParameter = JsonObject.create().put("claimSettlementId", pmnSettlementId).put("caseId", caseId);
            } else if (pmnSettlementId != null) {
                query = QueryConstant.GET_METADATA_FINAL_INVOICE;
                queryParameter = JsonObject.create().put("claimSettlementId", pmnSettlementId);
            } else {
                query = QueryConstant.GET_METADATA;
                queryParameter = JsonObject.create().put("caseId", caseId);
            }

            // LOGGER.info("query");
            // LOGGER.info(query);
            // LOGGER.info("query parameter");
            // LOGGER.info(queryParameter);

            QueryResult result = cluster.query(query,
                    queryOptions().parameters(queryParameter));

            if (!result.rowsAsObject().isEmpty()) {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onSucessResponse(result.rowsAsObject().get(0).toMap()));
            } else {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onNoContentResponse());
            }
        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }

    public Uni<javax.ws.rs.core.Response> getHnwi(String caseId) throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;
        String query = QueryConstant.GET_INFORMATION_HNWI_CASE;


        try {
            Cluster cluster = cbUtil.getCluster();
            QueryResult result = cluster.query(query,
                    queryOptions().parameters(JsonObject.create().put("caseId", caseId)));

            if (!result.rowsAsObject().isEmpty()) {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onSucessResponse(result.rowsAsObject().get(0).toMap()));
            } else {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onNoContentResponse());
            }
        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }


    public Uni<javax.ws.rs.core.Response> getLifeAssuredPolicyHolder(String caseId) throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;
        String query = QueryConstant.GET_LIFE_ASSURED_POLICY_HOLDER_FROM_CASE;


        try {
            Cluster cluster = cbUtil.getCluster();
            QueryResult result = cluster.query(query,
                    queryOptions().parameters(JsonObject.create().put("caseId", caseId)));

            if (!result.rowsAsObject().isEmpty()) {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onSucessResponse(result.rowsAsObject().get(0).toMap()));
            } else {
                couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onNoContentResponse());
            }
        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }

    public Uni<javax.ws.rs.core.Response> createDocument(Document document) throws Exception {

        Uni<javax.ws.rs.core.Response> couchbaseResponse = null;

        try {
            Cluster cluster = cbUtil.getCluster();
            QueryResult result = cluster.query(QueryConstant.CREATE_DOCUMENT,
                    queryOptions().parameters(JsonObject.create().put("documentId", document.getId()).put("documentValue", objectMapperUtil.objectToAnotherObject(document, Map.class))));

            //still not know what we need to get
            CouchbaseResponse cbResponse = new CouchbaseResponse();
            cbResponse.setResponseCode("00");
            cbResponse.setResponseMessage("SUCCESS");
            couchbaseResponse = Uni.createFrom().item("").onItem().transform(s -> responseUtil.onSucessResponse(cbResponse));

        } catch (final InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
            throw new InternalServerErrorException("error in query");
        } finally {

        }
        return couchbaseResponse;
    }
}
