package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.ExcanPermission;
import com.poscdx.odc.ampro015.domain.store.ExcanPermissionStore;
import com.poscodx.odc.ampro015.store.jpo.ExcanPermissionJpo;
import com.poscodx.odc.ampro015.store.repository.ExcanPermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExcanPermissionJpaStore
    implements ExcanPermissionStore
{
    private final ExcanPermissionRepository repository;

    public ExcanPermissionJpaStore(ExcanPermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExcanPermission retrieve(int id) {
        Optional<ExcanPermissionJpo> retVal = this.repository.findById(id);
        return retVal.map(ExcanPermissionJpo::toDomain).orElse(null);
    }

    @Override
    public List<ExcanPermission> retrieveAll() {
        List<ExcanPermissionJpo> retVal = this.repository.findAll();
        return ExcanPermissionJpo.toDomains(retVal);
    }

    @Override
    public ExcanPermission update(ExcanPermission entity) {
        ExcanPermissionJpo jpoToUpdate = new ExcanPermissionJpo(entity);
        ExcanPermissionJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public ExcanPermission create(ExcanPermission entity) {
        ExcanPermissionJpo jpoToSave = new ExcanPermissionJpo(entity);
        ExcanPermissionJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
