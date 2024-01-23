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
    private String requesterAvatar;
    private String requesterName;
    private String requesterId;
    public String toJson(){
        return JsonUtil.toJson(this);
    }

    public static IssueManagement fromJson(String json){
        return JsonUtil.fromJson(json, IssueManagement.class);
    }

    public IssueManagement(Object[] objects){
        this.seq = (int) objects[0];
        this.registrationDate = (Date) objects[1];
        this.requestDate = (String) objects[2];
        this.requester = (String) objects[3];
        this.site = (String) objects[4];
        this.module = (String) objects[5];
        this.menu = (String) objects[6];
        this.screenName = (String) objects[7];
        this.divisionFlag = (String) objects[8];
        this.appliedPeriodFlag = (String) objects[9];
        this.contents = (String) objects[10];
        this.contentsKR = (String) objects[11];
        this.fileName = (objects[12] == null || ((String) objects[12]).isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/" + objects[12];
        this.developer = (String) objects[13];
        this.acceptFlag = (String) objects[14];
        this.status = (String) objects[15];
        this.developComments = (String) objects[16];
        this.developFromDate = (String) objects[17];
        this.developToDate = (String) objects[18];
        this.categoryFlag = (String) objects[19];
        this.requesterConfirm = (String) objects[20];
        this.finalConfirmDate = (String) objects[21];
        this.requesterId = (String) objects[22];
//        this.requesterName = "http://172.25.219.61:8080/img/" + (String) objects[23];
    }

}
