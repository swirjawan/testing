package com.prudential.platform.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryConstant {
    public static final Properties properties;
    public static String GET_PMN_DOCTYPES;
    public static String GET_PMN_DOC;
    public static String GET_METADATA_FINAL_INVOICE;
    public static String GET_METADATA_CLAIM_SETTLEMENT_DETAIL;
    public static String GET_CLAIM_SETTLEMENT_BY_ID;
    public static String GET_METADATA;
    public static String GET_INFORMATION_HNWI_CASE;
    public static String GET_LIFE_ASSURED_POLICY_HOLDER_FROM_CASE;
    public static String CREATE_DOCUMENT;

    static {

        properties = new Properties();

        try (InputStream stream = QueryConstant.class.getClassLoader().getResourceAsStream("query.properties")) {
            properties.load(stream);

            GET_PMN_DOCTYPES = properties.getProperty("GetPmnDocTypes");
            GET_PMN_DOC = properties.getProperty("GetPmnDoc");
            GET_METADATA_FINAL_INVOICE = properties.getProperty("GetMetadataFinalInvoice");
            GET_METADATA_CLAIM_SETTLEMENT_DETAIL = properties.getProperty("GetMetadataClaimSettlementDetail");
            GET_CLAIM_SETTLEMENT_BY_ID = properties.getProperty("GetClaimSettlementById");
            GET_METADATA = properties.getProperty("GetMetadata");
            GET_INFORMATION_HNWI_CASE = properties.getProperty("GetInformationHnwiCase");
            GET_LIFE_ASSURED_POLICY_HOLDER_FROM_CASE = properties.getProperty("GetLifeAssuredPolicyHolderFromCase");
            CREATE_DOCUMENT = properties.getProperty("CreateDocument");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
