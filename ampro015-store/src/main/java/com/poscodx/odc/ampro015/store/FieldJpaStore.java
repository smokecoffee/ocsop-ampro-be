package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import com.poscodx.odc.ampro015.store.repository.FieldRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FieldJpaStore implements FieldStore {
    private final FieldRepository repository;

    public FieldJpaStore(FieldRepository repository) {
        this.repository = repository;
    }

    @Override
    public Field retrieve(int id) {
        Optional<FieldJpo> retVal = this.repository.findById(id);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<Field> retrieveAll() {
        return FieldJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Field update(Field entity) {
        FieldJpo jpoToUpdate = new FieldJpo(entity);
        FieldJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Field create(Field entity) {
        return this.repository.save(new FieldJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Field> retrieveList(Integer assetId) {
        return FieldJpo.toDomains(this.repository.findAllByAssetId(assetId));
    }

    @Override
    public List<Field> findByAssetId(int id) {
        return FieldJpo.toDomains(this.repository.findByAssetId(id));
    }
}
