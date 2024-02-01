package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class M99WorkingTime {
    private int seq;
    private String empId;
    private Date registerDate;
    private Date arrive;
    private Date leave;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
