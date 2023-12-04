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
public class Pme00ProjectInfo extends PoscoEntity {
    private String cdV;
    private int period;
    private String koreaPm;
    private String vietnamPl;
    private String status;
    private String framework;
    private Date startDate;
    private Date endDate;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00ProjectInfo fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00ProjectInfo.class);
    }
}
