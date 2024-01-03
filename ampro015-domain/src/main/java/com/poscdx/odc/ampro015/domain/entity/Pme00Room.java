package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pme00Room {

   private int cdTpId;
   private String cdV;
   private String cdvMeaning;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Room fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Room.class);
    }
}
