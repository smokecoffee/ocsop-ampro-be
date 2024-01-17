package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueManagement {
    private int seq;
    private Date registration_Date;
    private String request_Date;
    private String requester;
    private String site;
    private String module;
    private String menu;
    private String screen_Name;
    private String divisionFlag;
    private String applied_Period_Flag;
    private String contents;
    private String contents_KR;
    private String file_Name;
    private String developer;
    private String accept_Flag;
    private String status;
    private String develop_Comments;
    private String develop_From_Date;
    private String category_Flag;
    private String request_Confirm;
    private String final_Confirm_Date;
}
