package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Permission;
import com.poscdx.odc.ampro015.domain.store.Pme00PermissionStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00PermissionJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00PermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00PermissionJpaStore
    implements Pme00PermissionStore
{
    private final Pme00PermissionRepository repository;

    public Pme00PermissionJpaStore(Pme00PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Permission retrieve(int id) {
        Optional<Pme00PermissionJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00PermissionJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00Permission> retrieveAll() {
        List<Pme00PermissionJpo> retVal = this.repository.findAll();
        return Pme00PermissionJpo.toDomains(retVal);
    }

    @Override
    public Pme00Permission update(Pme00Permission entity) {
        Pme00PermissionJpo jpoToUpdate = new Pme00PermissionJpo(entity);
        Pme00PermissionJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Permission create(Pme00Permission entity) {
        Pme00PermissionJpo jpoToSave = new Pme00PermissionJpo(entity);
        Pme00PermissionJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
