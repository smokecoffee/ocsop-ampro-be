package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class PmeSiteResponse {
    private int status;
    private List<Pme00Site> listData;
    private String message;
}
