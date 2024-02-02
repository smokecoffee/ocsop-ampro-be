package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.List;
@Data
public class PmeStatusResponse {
    private int status;
    private List<Pme00Status> listData;
    private String message;
}
