
package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;
import com.poscdx.odc.ampro015.domain.store.ProductHistoryStore;
import com.poscdx.odc.ampro015.domain.spec.ProductHistoryService;
import com.poscoict.base.share.domain.NameValueList;

import java.util.List;

/**
 * ProductHistoryService.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
public class ProductHistoryLogic implements ProductHistoryService {

    private final ProductHistoryStore store;

    public ProductHistoryLogic(ProductHistoryStore store) {
        this.store = store;
    }

    @Override
    public ProductHistory register(ProductHistory entity) {
        return this.store.create(entity);
    }

    @Override
    public List<ProductHistory> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public ProductHistory find(String id) {
        return this.store.retrieve(id);
    }

    @Override
    public void modify(String id, NameValueList nameValue) {
        ProductHistory entity = this.store.retrieve(id);
        entity.setValues(nameValue);
        this.store.update(entity);
    }

    @Override
    public void remove(String id) {
        this.store.delete(id);
    }
}