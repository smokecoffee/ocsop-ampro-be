package com.poscdx.odc.ampro015.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pme00AllLevel2EmployeeResponse {
    private int status;
    private List<Pme00Employee> listData;
    private String message;
}
