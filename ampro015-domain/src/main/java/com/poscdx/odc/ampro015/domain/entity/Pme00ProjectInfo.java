package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;

/**
 * Pme00ProjectInfo
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00ProjectInfo {
    private String cdV;
    private int period;
    private String koreaPm;
    private String vietnamPl;
    private String status;
    private String framework;
    private Date startDate;
    private Date endDate;
    private String image;
    private String koreaPmName;
    private String koreaPmImage;
    private String vietnamPlName;
    private String vietnamPlImage;

    public Pme00ProjectInfo(String cdV) {
        this.cdV = cdV;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00ProjectInfo fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00ProjectInfo.class);
    }

    public Pme00ProjectInfo(Object[] objects) {

        this.cdV = (String) objects[0];
        this.period = (int) objects[1];
        this.koreaPm = (String) objects[2];
        this.vietnamPl = (String) objects[3];
        this.status = (String) objects[4];
        this.framework = (String) objects[5];
        this.startDate = (Date) objects[6];
        this.endDate = (Date) objects[7];
        this.image = (objects[8] == null || ((String) objects[8]).isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/" + objects[8];
        this.koreaPmName = (String) objects[9];
        this.koreaPmImage = "http://172.25.219.61:8080/img/" + (String) objects[10];
        this.vietnamPlName = (String) objects[11];
        this.vietnamPlImage = "http://172.25.219.61:8080/img/" + (String) objects[12];
    }
}
