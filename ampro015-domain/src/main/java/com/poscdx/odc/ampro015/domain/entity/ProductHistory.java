/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-domain
 * Source File Name   : com.posco.mes3.domain.entity.ProductHistory
 * Description        :
 * Author             : poscouser
 * Version            : 1.0.0
 * File Name related  :
 * Class Name related :
 * Created Date       : ${date}
 * Updated Date       : ${date}
 * Last modifier      : poscouser
 * Updated content    : 최초작성
 *
 *==================================================================================*/
package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * ProductHistory.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@Getter
@Setter
public class ProductHistory extends PoscoEntity {

    private String id;
    private String mtlNo;

    public ProductHistory() {
        this.id = UUID.randomUUID().toString();
    }

    public ProductHistory(String id) {
        this.id = id;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static ProductHistory fromJson(String json) {
        return JsonUtil.fromJson(json, ProductHistory.class);
    }
}