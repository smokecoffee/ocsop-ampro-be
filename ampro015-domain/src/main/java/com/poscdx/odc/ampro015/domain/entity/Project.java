package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends PoscoEntity {
    private int id;
    private int category;
    private String projectID;
    private String createProID;
    private Date createTime;
    private String lastUpdateID;
    private Date lastUpdateTime;
    private String meaning;
    private String explain;
    private int inquirySeq;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Project fromJson(String json) {
        return JsonUtil.fromJson(json, Project.class);
    }
}
