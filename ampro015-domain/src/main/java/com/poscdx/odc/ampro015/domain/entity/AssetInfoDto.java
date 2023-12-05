package com.poscdx.odc.ampro015.domain.entity;


import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfoDto {
    private Asset asset;
    private List<Field> fields;
    private List<Image> images;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static AssetInfoDto fromJson(String json) {
        return JsonUtil.fromJson(json, AssetInfoDto.class);
    }
}
