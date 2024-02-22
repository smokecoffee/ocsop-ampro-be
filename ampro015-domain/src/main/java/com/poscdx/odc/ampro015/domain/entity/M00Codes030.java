package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * M00Codes030
 *
 * @author 202284_Lam
 * @since : 2023-11-28
 */
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

    public M00Codes030(String cdV) {
        this.cdV = cdV;
    }


}
