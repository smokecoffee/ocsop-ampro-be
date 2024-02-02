package com.poscdx.odc.ampro015.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class M00TaskId implements Serializable {
    private String projectNumber;
    private String taskName;

    @Override
    public String toString() {
        return "M00TaskId{" +
                "projectNumber='" + projectNumber + '\'' +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
