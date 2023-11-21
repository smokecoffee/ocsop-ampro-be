/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-store
 * Source File Name   : com.posco.mes3.ra.store.jpa.repository.ProductHistoryRepository
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
package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ProductHistoryJpo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductHistoryRepository.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
public interface ProductHistoryRepository extends JpaRepository<ProductHistoryJpo, String> {
}