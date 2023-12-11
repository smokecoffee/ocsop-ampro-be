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
    private String status;
    private String framework;
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
        this.KoreaPm = (String) objects[2];
        this.VietNamPl = (String) objects[3];
        this.status = (String) objects[4];
        this.framework = (String) objects[5];
        this.startDate = (Date) objects[6];
        this.endDate = (Date) objects[7];
        this.KoreaPmName = (String) objects[8];
        this.VietNamName = (String) objects[9];
        this.cdvMeaning = (String) objects[10];
        this.cdVExplain = (String) objects[11];
        this.cdTpId = (int) objects[12];
        this.categoryGroupId = (int) objects[13];
        this.cdVInquirySeq = (int) objects[14];
        this.createdProgramId = (String) objects[15];
        this.creationTimestamp = (Date) objects[16];
        this.lastUpdateProgramId = (String) objects[17];
        this.lastUpdateTimestamp = (Date) objects[18];
    }
}
