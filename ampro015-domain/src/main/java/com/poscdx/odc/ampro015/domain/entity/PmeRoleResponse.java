package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.List;
@Data
public class PmeRoleResponse {

    private int status;
    private List<Pme00Role> listData;
    private String message;
}
