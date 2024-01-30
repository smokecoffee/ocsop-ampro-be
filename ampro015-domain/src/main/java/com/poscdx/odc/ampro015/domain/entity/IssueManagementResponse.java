package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

@Data
public class IssueManagementResponse {
    private int status;
    private IssueManagement data;
    private String message;
}
