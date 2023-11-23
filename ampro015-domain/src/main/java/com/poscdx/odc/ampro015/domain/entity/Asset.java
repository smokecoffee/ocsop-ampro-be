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
public class Asset extends PoscoEntity {
    private int id;
    private String token;
    private String owner;
    private int duration;
    private String qrcode;
    private int status;
    private int createBy;
    private Date createAt;
    private int updateBy;
    private Date updateAt;
    private Date deleteAt;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
