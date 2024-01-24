package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class M00Task {
    private String projectNumber;
    private String taskName;
    private String taskExplain;
    private String empId;
    private String status;
    private Date planDate;
    private Date actualEndDate;
    private String remark;
    private Date creationTimestamp;
    private Date lastUpdateTimestamp;
    private String lastUpdateId;
    private String writer;
    private String password;
    private String category;
    private String passwordRequest;
    private Date startDate;
    private String statusApprove;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static M00Task fromJson(String json) {
        return JsonUtil.fromJson(json, M00Task.class);
    }

    public M00Task(Object[] object) {
        this.projectNumber = (String) object[0];
        this.taskName = (String) object[1];
        this.taskExplain = (String) object[5];
        this.status = (String) object[6];
        this.planDate = (Date) object[7];
        this.actualEndDate = (Date) object[8];
        this.remark = (String) object[9];
        this.lastUpdateTimestamp = (Date) object[10];
        this.lastUpdateId = (String) object[12];
        this.writer = (String) object[12];
        this.empId = (String) object[2];
        this.password = (String) object[13];
        this.category = (String) object[14];
        this.creationTimestamp = (Date) object[15];
        this.startDate = (Date) object[16];
        this.statusApprove = (String) object[17];
    }
}
