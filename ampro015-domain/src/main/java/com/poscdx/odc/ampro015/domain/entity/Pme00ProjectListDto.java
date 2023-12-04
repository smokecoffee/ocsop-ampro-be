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
    private String VietNamPl;
    private int status;
    private String framework;
    private String description;
    private List<Pme00Member> lstMember;
    private List<Pme00Member> lstMemberName;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00ProjectListDto fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00ProjectListDto.class);
    }
}
