package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleUserStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00RoleUserJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00RoleUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00RoleUserJpaStore implements Pme00RoleUserStore {

    private final Pme00RoleUserRepository repository;

    public Pme00RoleUserJpaStore(Pme00RoleUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00RoleUser retrieve(int id) {
        Optional<Pme00RoleUserJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00RoleUserJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00RoleUser> retrieveAll() {
        return Pme00RoleUserJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00RoleUser update(Pme00RoleUser Pme00RoleUser) {
        Pme00RoleUserJpo jpoToUpdate = new Pme00RoleUserJpo(Pme00RoleUser);
        Pme00RoleUserJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00RoleUser create(Pme00RoleUser entity) {
        return this.repository.save(new Pme00RoleUserJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
