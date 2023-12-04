package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date lastDeleteTimestamp;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static M00Task fromJson(String json) {
        return JsonUtil.fromJson(json, M00Task.class);
    }
}
