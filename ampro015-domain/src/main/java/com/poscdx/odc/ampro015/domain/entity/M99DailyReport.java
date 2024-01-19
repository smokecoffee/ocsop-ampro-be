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
    private String empName;
    private Date fromDate;
    private Date toDate;

    public M99DailyReport(Object[] object) {
        this.seq = (int) object[0];
        this.employeeId = (String) object[1];
        this.registerDate = (Date) object[2];
        this.projectNumber = (String) object[3];
        this.contents = (String) object[4];
        this.remarks = (String) object[5];
        this.fileName = (String) object[6];
        this.opSatDate = (Date) object[7];
        this.opDnDate = (Date) object[8];
        this.creationTimestamp = (Date) object[9];
        this.lastUpdateTimestamp = (Date) object[10];
        this.status = object[11] == null ? 0 : (int) object[11];
        this.empName = (String) object[12];
    }

}
