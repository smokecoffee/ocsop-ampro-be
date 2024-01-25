package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Gender {

    private String cdV;
    private String cdvMeaning;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Gender fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Gender.class);
    }
}
