package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Role;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00RoleJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00RoleJpaStore implements Pme00RoleStore {

    private final Pme00RoleRepository repository;

    public Pme00RoleJpaStore(Pme00RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Role retrieve(int id) {
        Optional<Pme00RoleJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00RoleJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00Role> retrieveAll() {
        return Pme00RoleJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00Role update(Pme00Role Pme00Role) {
        Pme00RoleJpo jpoToUpdate = new Pme00RoleJpo(Pme00Role);
        Pme00RoleJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Role create(Pme00Role entity) {
        return this.repository.save(new Pme00RoleJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
