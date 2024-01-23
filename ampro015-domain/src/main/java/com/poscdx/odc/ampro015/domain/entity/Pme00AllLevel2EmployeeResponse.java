package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pme00AllLevel2EmployeeResponse {
    private int status;
    private List<Pme00Employee> listData;
    private String message;
}
