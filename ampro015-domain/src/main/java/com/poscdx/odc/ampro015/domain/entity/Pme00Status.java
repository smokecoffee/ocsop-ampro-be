package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.Data;

@Data
public class Pme00Status {

    private String CD_V;
    private String CD_V_MEANING;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Status fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Status.class);
    }
}
