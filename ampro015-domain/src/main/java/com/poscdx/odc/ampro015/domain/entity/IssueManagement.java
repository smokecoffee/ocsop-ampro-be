package com.poscdx.odc.ampro015.domain.entity;

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
    public String toJson(){
        return JsonUtil.toJson(this);
    }

    public static IssueManagement fromJson(String json){
        return JsonUtil.fromJson(json, IssueManagement.class);
    }

}
