package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Site {

    private String CD_V;
    private String CD_V_MEANING;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Site fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Site.class);
    }
}
