package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00EmployeeTask {
    private String projectNumber;
    private String taskName;
    private String empId;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00EmployeeTask fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00EmployeeTask.class);
    }
}
