package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pme00MeetingResponse {
    private int status;
    private Pme00Meeting data;
    private String message;
}