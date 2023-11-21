/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-domain
 * Source File Name   : com.posco.mes3.domain.store.ProductHistoryStore
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
package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;

import java.util.List;

/**
 * ProductHistoryStore.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
public interface ProductHistoryStore {

    ProductHistory create(ProductHistory entity);

    ProductHistory retrieve(String id);

    void update(ProductHistory entity);

    void delete(String id);

    List<ProductHistory> retrieveAll();
}