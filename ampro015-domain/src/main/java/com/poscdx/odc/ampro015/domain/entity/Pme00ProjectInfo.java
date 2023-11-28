package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00ProjectInfo extends PoscoEntity {
    private String cdVId;
    private int period;
    private String koreaPm;
    private String vietnamPl;
    private String status;
    private String framework;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Field fromJson(String json) {
        return JsonUtil.fromJson(json, Field.class);
    }
}
