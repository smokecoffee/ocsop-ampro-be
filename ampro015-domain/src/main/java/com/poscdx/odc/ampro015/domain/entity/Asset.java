package com.poscdx.odc.ampro015.domain.entity;

import com.google.gson.annotations.JsonAdapter;
import com.poscdx.odc.ampro015.domain.utils.DateUtils;
import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset  {
    private int id;
    private String token;
    private String owner;
    private int duration;
    private String qrcode;
    private int status;
    private String createBy;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date createAt;
    private String updateBy;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date updateAt;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date deleteAt;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
