package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00PerRole;
import com.poscdx.odc.ampro015.domain.store.Pme00PerRoleStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00PerRoleJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00PerRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00PerRoleJpaStore
    implements Pme00PerRoleStore
{
    private final Pme00PerRoleRepository repository;

    public Pme00PerRoleJpaStore(Pme00PerRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00PerRole retrieve(int id) {
        Optional<Pme00PerRoleJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00PerRoleJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00PerRole> retrieveAll() {
        List<Pme00PerRoleJpo> retVal = this.repository.findAll();
        return Pme00PerRoleJpo.toDomains(retVal);
    }

    @Override
    public Pme00PerRole update(Pme00PerRole entity) {
        Pme00PerRoleJpo jpoToUpdate = new Pme00PerRoleJpo(entity);
        Pme00PerRoleJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00PerRole create(Pme00PerRole entity) {
        Pme00PerRoleJpo jpoToSave = new Pme00PerRoleJpo(entity);
        Pme00PerRoleJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
