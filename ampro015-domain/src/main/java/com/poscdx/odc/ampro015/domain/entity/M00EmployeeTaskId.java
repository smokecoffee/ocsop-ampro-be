package com.poscdx.odc.ampro015.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class M00EmployeeTaskId implements Serializable {
    private String projectNumber;
    private String taskName;

    private String empId;
}


