package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import com.poscodx.odc.ampro015.store.repository.FieldRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class FieldJpaStore implements FieldStore {
    private final FieldRepository repository;

    public FieldJpaStore(FieldRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Field> retrieve(Integer assetId) {
        return FieldJpo.toDomains(this.repository.findAllByAssetId(assetId));
    }

    @Override
    public List<Field> retrieveAll() {
        return null;
    }

    @Override
    public Field update(Field entity) {
        return null;
    }

    @Override
    public Field create(Field entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
