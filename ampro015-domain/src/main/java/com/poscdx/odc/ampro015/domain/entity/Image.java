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
public class Image extends PoscoEntity {
    private int id;
    private int assetId;
    private String name;
    private String originalName;
    private String path;
    private String createBy;
    private Date createAt;
    private String updateBy;
    private Date updateAt;
    private Date deleteAt;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Image fromJson(String json) {
        return JsonUtil.fromJson(json, Image.class);
    }
}
