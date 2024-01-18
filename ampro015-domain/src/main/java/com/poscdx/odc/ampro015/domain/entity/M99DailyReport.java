package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class M99DailyReport {

    private int seq;
    private String employeeId;
    private Date registerDate;
    private String projectNumber;
    private String contents;
    private String remarks;
    private String fileName;
    private Date opSatDate;
    private Date opDnDate;
    private Date creationTimestamp;
    private Date lastUpdateTimestamp;
    private int status;

}
