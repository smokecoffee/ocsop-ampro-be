package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IssueManagementDto implements Serializable {
    private int seq;
    private Date registrationDate;
    private String requestDate;
    private String requester;
    private String site;
    private String module;
    private String menu;
    private String screenName;
    private String divisionFlag;
    private String appliedPeriodFlag;
    private String contents;
    private String contentsKR;
    private String fileName;
    private String developer;
    private String acceptFlag;
    private String status;
    private String developComments;
    private String developFromDate;
    private String developToDate;
    private String categoryFlag;
    private String requesterConfirm;
    private String finalConfirmDate;
    private String photo;

    public IssueManagementDto(Object[] objects){
        this.seq = (int) objects[0];
        this.registrationDate = (Date) objects[1];
        this.requestDate = (String) objects[2];
        this.requester = (String) objects[3];
        this.site = (String) objects[4];
        this.module = (String) objects[5];
        this.screenName = (String) objects[6];
        this.divisionFlag = (String) objects[7];
        this.appliedPeriodFlag = (String) objects[8];
        this.contents = (String) objects[9];
        this.contentsKR = (String) objects[10];
        this.fileName = (String) objects[11];
        this.developer = (String) objects[12];
        this.acceptFlag = (String) objects[13];
        this.status = (String) objects[14];
        this.developComments = (String) objects[15];
        this.developFromDate = (String) objects[16];
        this.developToDate = (String) objects[17];
        this.categoryFlag = (String) objects[18];
        this.requesterConfirm = (String) objects[19];
        this.finalConfirmDate = (String) objects[20];
        this.photo = (String) objects[21];
    }
}
