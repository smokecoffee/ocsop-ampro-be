/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-store
 * Source File Name   : com.posco.mes3.ra.store.jpa.ProductHistoryJpaStore
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
package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;
import com.posco.reuse.common.errorobjects.PosBaseException;
import org.springframework.stereotype.Repository;

import com.poscdx.odc.ampro015.domain.store.ProductHistoryStore;
import com.poscodx.odc.ampro015.store.jpo.ProductHistoryJpo;
import com.poscodx.odc.ampro015.store.repository.ProductHistoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * ProductHistoryJpaStore.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@Repository
public class ProductHistoryJpaStore implements ProductHistoryStore {

    private final ProductHistoryRepository repository;

    public ProductHistoryJpaStore(ProductHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductHistory create(ProductHistory entity) {
        ProductHistoryJpo saved = this.repository.save(new ProductHistoryJpo(entity));
        return saved.toDomain();
    }

    @Override
    public List<ProductHistory> retrieveAll() {
        return ProductHistoryJpo.toDomains(this.repository.findAll());
    }

    @Override
    public ProductHistory retrieve(String id) {
        Optional<ProductHistoryJpo> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            ProductHistoryJpo jpo = optional.get();
            return jpo.toDomain();
        } else {
            throw new PosBaseException("No such element for id " + id);
        }
    }

    @Override
    public void update(ProductHistory entity) {
        this.repository.save(new ProductHistoryJpo(entity));
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}