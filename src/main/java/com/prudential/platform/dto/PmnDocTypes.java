package com.prudential.platform.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RegisterForReflection
@ToString
public class PmnDocTypes {

    private String[] pmnDocTypes;

    public String[] getPmnDocTypes() {
        return pmnDocTypes;
    }

    public void setPmnDocTypes(String[] pmnDocTypes) {
        this.pmnDocTypes = pmnDocTypes;
    }

}
