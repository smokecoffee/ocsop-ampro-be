package com.poscdx.odc.ampro015.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * M00Codes030Id
 *
 * @author 202284_Lam
 * @since : 2023-11-28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class M00Codes030Id implements Serializable {

    private int cdTpId;
    private int categoryGroupId;
    private String cdV;

}
