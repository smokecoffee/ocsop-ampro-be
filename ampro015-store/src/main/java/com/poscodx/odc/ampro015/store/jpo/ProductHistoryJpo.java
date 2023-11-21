/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-store
 * Source File Name   : com.posco.mes3.ra.store.jpa.jpo.ProductHistoryJpo
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
package com.poscodx.odc.ampro015.store.jpo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import com.poscoict.base.share.jpo.PoscoEntityJpo;
import com.posco.reuse.common.errorobjects.PosBaseException;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;

/**
 * ProductHistoryJpo.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ProductHistory")
@Table(name = "TB_ProductHistory")
public class ProductHistoryJpo extends PoscoEntityJpo {

    @Id
    private String id;
    private String mtlNo;

    public ProductHistoryJpo(ProductHistory domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public ProductHistory toDomain() {
        ProductHistory domainEntity = new ProductHistory();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<ProductHistory> toDomains(Iterable<ProductHistoryJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(ProductHistoryJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void validateJpo() throws PosBaseException {

    }
}