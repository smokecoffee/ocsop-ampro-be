/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-domain
 * Source File Name   : com.posco.mes3.domain.spec.ProductHistoryService
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
package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;
import com.poscoict.base.share.domain.NameValueList;

import java.util.List;

/**
 * ProductHistoryService.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
public interface ProductHistoryService {

    ProductHistory register(ProductHistory entity);

    ProductHistory find(String id);

    void modify(String id, NameValueList nameValue);

    void remove(String id);

    List<ProductHistory> findAll();
}