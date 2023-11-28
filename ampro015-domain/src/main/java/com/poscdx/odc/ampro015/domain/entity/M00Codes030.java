package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class M00Codes030 {

    private int cdTpId;
    private int categoryGroupId;
    private String cdV;
    private String cdvMeaning;
    private String cdVExplain;
    private int cdVInquirySeq;
    private String createdProgramId;
    private Date creationTimestamp;
    private String lastUpdateProgramId;
    private Date lastUpdateTimestamp;


    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
