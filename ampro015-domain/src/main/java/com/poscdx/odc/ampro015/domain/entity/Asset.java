package com.poscdx.odc.ampro015.domain.entity;

import com.google.gson.annotations.JsonAdapter;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
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

    private String ownerName;
    private String ownerImage;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }

    public Asset(Object[] objects) {
        this.id = (int) objects[0];
        this.token = (String) objects[1];
        this.owner = (String) objects[2];
        this.duration = (int) objects[3];
        this.qrcode = (String) objects[4];
        this.status = (int) objects[5];
        this.createBy = (String) objects[6];
        this.createAt = (Date) objects[7];
        this.updateBy =(String) objects[8];
        this.updateAt = (Date) objects[9];
        this.deleteAt = (Date) objects[10];
        this.ownerName = (String) objects[11];
        this.ownerImage = ConstantUtil.applyEmployeeAvatarPath((String) objects[12], "Employee");
    }
}
