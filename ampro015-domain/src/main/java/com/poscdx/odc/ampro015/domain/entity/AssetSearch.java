package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetSearch extends PoscoEntity {
    private String owner;
    private int status;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static AssetSearch fromJson(String json) {
        return JsonUtil.fromJson(json, AssetSearch.class);
    }
}
