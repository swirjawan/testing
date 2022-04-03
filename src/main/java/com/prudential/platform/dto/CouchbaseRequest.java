package com.prudential.platform.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import javax.json.JsonObject;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RegisterForReflection
@ToString
public class CouchbaseRequest {

    JsonObject param;
}
