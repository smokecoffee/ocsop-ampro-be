package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00ProjectListDto {

    private String cdV;
    private int period;
    private Date startDate;
    private Date endDate;
    private String KoreaPm;
    private String KoreaPmName;
    private String VietNamPl;
    private String VietNamName;
    private int status;
    private String framework;
    private String description;
    private int cdTpId;
    private int categoryGroupId;
    private String cdvMeaning;
    private String cdVExplain;
    private int cdVInquirySeq;
    private String createdProgramId;
    private Date creationTimestamp;
    private String lastUpdateProgramId;
    private Date lastUpdateTimestamp;

    private List<Pme00Member> lstMember;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00ProjectListDto fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00ProjectListDto.class);
    }

    public Pme00ProjectListDto(Object[] objects) {
        this.cdV = (String) objects[0];
        this.period = (int) objects[1];
        this.startDate = (Date) objects[2];
        this.endDate = (Date) objects[3];
        this.KoreaPm = (String) objects[4];
        this.KoreaPmName = (String) objects[5];
        this.VietNamPl = (String) objects[6];
        this.VietNamName = (String) objects[7];
        this.status = (int) objects[8];
        this.framework = (String) objects[9];
        this.description = (String) objects[10];
        this.cdTpId = (int) objects[11];
        this.categoryGroupId = (int) objects[12];
        this.cdvMeaning = (String) objects[13];
        this.cdVExplain = (String) objects[14];
        this.cdVInquirySeq = (int) objects[15];
        this.createdProgramId = (String) objects[16];
        this.creationTimestamp = (Date) objects[17];
        this.lastUpdateProgramId = (String) objects[18];
        this.lastUpdateTimestamp = (Date) objects[19];
    }
}
