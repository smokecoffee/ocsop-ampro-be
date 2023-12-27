package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Role {
    private int id;
    private String name;
    private String description;
    private int status;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Role fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Role.class);
    }
}
