package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CheckOnlyDateBookMeeting {
    private Date startOnlyDate;
    private Date endOnlyDate;
}
