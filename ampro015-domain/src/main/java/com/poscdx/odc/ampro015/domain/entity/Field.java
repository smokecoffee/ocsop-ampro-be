package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field extends PoscoEntity {
    private int id;
    private int assetId;
    private int orderBy;
    private String name;
    private String value;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Field fromJson(String json) {
        return JsonUtil.fromJson(json, Field.class);
    }
}
