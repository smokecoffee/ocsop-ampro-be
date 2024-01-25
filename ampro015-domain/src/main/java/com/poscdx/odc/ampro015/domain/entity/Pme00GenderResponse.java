package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.List;

@Data
public class Pme00GenderResponse {

    private int status;
    private List<Pme00Gender> listData;
    private String message;
}
