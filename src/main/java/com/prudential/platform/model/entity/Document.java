package com.prudential.platform.model.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.constraint.Nullable;
import lombok.*;

import java.util.Date;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RegisterForReflection
@ToString
public class Document {

    private String id;
    private String status;
    private String type;
    private String group;
    private Map<String, Object> data;
    private Map<String, Object> metadata;
    private Map<String, Object> references;
    private String method;
    private String tempName;
    private String docType;
    private String apiKey;
    @Nullable
    private Date created;
    private String fileExtension;
    private String mime;
    private String filename;
    private int attempt;
    private long size;

}