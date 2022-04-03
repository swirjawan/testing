package com.prudential.platform.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RegisterForReflection
@ToString
public class CouchbaseResponse {

    public String responseCode;
    public String responseMessage;

}