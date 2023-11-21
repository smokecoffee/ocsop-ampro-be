/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-service
 * Source File Name   : com.posco.mes3.domain.logic.ProductHistoryLogic
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
package com.poscodx.odc.ampro015.service.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.poscdx.odc.ampro015.domain.logic.ProductHistoryLogic;
import com.poscdx.odc.ampro015.domain.store.ProductHistoryStore;

/**
 * ProductHistoryLogic.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductHistorySpringLogic extends ProductHistoryLogic {

    public ProductHistorySpringLogic(ProductHistoryStore store) {
        super(store);
    }
}