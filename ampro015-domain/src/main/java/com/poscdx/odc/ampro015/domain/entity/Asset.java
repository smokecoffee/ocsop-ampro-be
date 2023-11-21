package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset extends PoscoEntity {
    private int id;
    private String token;
    private String owner;
    private int duration;
    private String qrcode;
    private int status;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
