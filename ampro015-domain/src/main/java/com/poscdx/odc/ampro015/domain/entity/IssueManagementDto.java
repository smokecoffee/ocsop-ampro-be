package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.tool.schema.spi.ScriptTargetOutput;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IssueManagementDto implements Serializable {
    private IssueManagement issueManagement;
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
    private String requesterId;
    private String developerName;
    private String requesterAvatar;

    public IssueManagementDto(Object[] objects){
        issueManagement = new IssueManagement(objects);
        this.developerName = (String) objects[23];
        this.requesterAvatar = ConstantUtil.applyEmployeeAvatarPath((String) objects[24], "Employee");
    }
}
