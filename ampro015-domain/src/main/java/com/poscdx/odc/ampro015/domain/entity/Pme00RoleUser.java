package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00RoleUser {
    private int id;
    private String empId;
    private int roleId;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00RoleUser fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00RoleUser.class);
    }
}
