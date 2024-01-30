package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IssueManagement {
    private int seq;
    private String seqString;
    private Date registrationDate;
    private Date requestDate;
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
    private Date developFromDate;
    private Date developToDate;
    private String categoryFlag;
    private String requesterConfirm;
    private Date finalConfirmDate;
    private String requesterId;
    //private String developerName;


    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static IssueManagement fromJson(String json) {
        return JsonUtil.fromJson(json, IssueManagement.class);
    }

    public IssueManagement(Object[] objects) {
        this.seq = (int) objects[0];
        this.registrationDate = (Date) objects[1];
        this.seqString = "CD-" + seq;
        this.requester = (String) objects[3];
        this.site = (String) objects[4];
        this.module = (String) objects[5];
        this.menu = (String) objects[6];
        this.screenName = (String) objects[7];
        this.divisionFlag = (String) objects[8];
        this.appliedPeriodFlag = (String) objects[9];
        this.contents = (String) objects[10];
        this.contentsKR = (String) objects[11];
        //this.fileName = ConstantUtil.applyEmployeeAvatarPath((String) objects[12], "Issues");
        this.fileName = (objects[12] == null || ((String) objects[12]).isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/Employee/" + objects[12];
        this.developer = (String) objects[13];
        this.acceptFlag = (String) objects[14];
        this.status = (String) objects[15];
        this.developComments = (String) objects[16];
        this.categoryFlag = (String) objects[19];
        this.requesterConfirm = (String) objects[20];
        this.requestDate = (Date) objects[23];
        this.developFromDate = (Date) objects[24];
        this.requesterId = (String) objects[22];
        this.developToDate = (Date) objects[25];
        this.finalConfirmDate = (Date) objects[26];
        // this.developerName = (String) objects[27];
    }

}
