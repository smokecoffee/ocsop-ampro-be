package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pme00AllMeetingResponse {
    private int status;
    private List<Pme00Meeting> listData;
    private String message;
}
