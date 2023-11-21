package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends PoscoEntity {
    private int id;
    private int assetId;
    private String name;
    private String originalName;
    private String path;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Image fromJson(String json) {
        return JsonUtil.fromJson(json, Image.class);
    }
}
