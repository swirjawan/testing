/**
 * @author garryalfa
 */

package com.prudential.platform.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.vertx.ext.web.handler.sockjs.impl.StringEscapeUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ApplicationScoped
public class ObjectMapperUtil {

    ObjectMapper objectMapper = new ObjectMapper();

    public Object jsonToModel(String json, String packagePath)
            throws ClassNotFoundException, JsonMappingException, JsonProcessingException {

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Class<?> classForName = Class.forName(packagePath);

        return objectMapper.readValue(json, classForName);
    }

    public Object jsonToModel(String json, Class<?> classForName)
            throws ClassNotFoundException, JsonMappingException, JsonProcessingException {

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // objectMapper.setDateFormat(dateFormat);
        return objectMapper.readValue(json, classForName);
    }

    public String objectToJson(Object object) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = objectMapper.writeValueAsString(object);

        return json;

    }

    public Object objectToAnotherObject(Object object, Class<?> classForName) {
        objectMapper = new ObjectMapper();
        return objectMapper.convertValue(object, classForName);

    }

    public Object parseJsonTree(String json) throws Exception {

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode root = objectMapper.readTree(json);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        String jsonValue;


        if (root.isObject()) {
            //System.out.println("OBJECT");
            ObjectNode objectNode = (ObjectNode) root;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();

            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                //System.out.println(entry.getKey());
                //System.out.println(entry.getValue());
                try {
                    jsonValue = StringEscapeUtils.unescapeJava(entry.getValue().textValue());
                } catch (Exception e) {
                    jsonValue = entry.getValue().textValue();
                }
                //System.out.println(jsonValue);
                returnMap.put(entry.getKey(), jsonToModel(jsonValue, Object.class));
            }

        } else if (root.isArray()) {
            //TO-DO
            //System.out.println("ARRAY");
        }

        return returnMap;
    }

}