package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class M00Codes020 {
    private int cdTpId;
    private String cdTp;
    private String createdProgramId;
    private Date creationTimestamp;
    private String lastUpdateProgramId;
    private Date lastUpdateTimestamp;
    private Date startActiveDate;
    private Date endActiveDate;
    private String useTp;
    private String cdTpVersion;
    private String versionUpCauseDesc;
    private String cdTpMeaning;
    private String cdTpExplain;
    private String mdlRegReqEmpId;
    private int fkCdTpId;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Asset fromJson(String json) {
        return JsonUtil.fromJson(json, Asset.class);
    }
}
