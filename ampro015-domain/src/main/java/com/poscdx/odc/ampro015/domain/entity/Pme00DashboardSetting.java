package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00DashboardSetting {
    private int id;
    private String empId;
    private String order;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00DashboardSetting fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00DashboardSetting.class);
    }


}
