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
public class Field extends PoscoEntity {
    private int id;
    private int assetId;
    private int orderBy;
    private String name;
    private String value;
    private String createBy;
    private Date createAt;
    private String updateBy;
    private Date updateAt;
    private Date deleteAt;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Field fromJson(String json) {
        return JsonUtil.fromJson(json, Field.class);
    }
}
