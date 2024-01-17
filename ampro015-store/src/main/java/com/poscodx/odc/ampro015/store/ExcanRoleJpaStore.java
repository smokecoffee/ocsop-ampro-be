package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.ERole;
import com.poscdx.odc.ampro015.domain.entity.ExcanRole;
import com.poscdx.odc.ampro015.domain.store.ExcanRoleStore;
import com.poscodx.odc.ampro015.store.jpo.ExcanPermissionJpo;
import com.poscodx.odc.ampro015.store.jpo.ExcanRoleJpo;
import com.poscodx.odc.ampro015.store.repository.ExcanRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ExcanRoleJpaStore
    implements ExcanRoleStore
{
    private final ExcanRoleRepository repository;

    public ExcanRoleJpaStore(ExcanRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExcanRole retrieve(int id) {
        Optional<ExcanRoleJpo> retVal = this.repository.findById(id);
        return retVal.map(ExcanRoleJpo::toDomain).orElse(null);
    }

    @Override
    public List<ExcanRole> retrieveAll() {
        List<ExcanRoleJpo> retVal = this.repository.findAll();
        return ExcanRoleJpo.toDomains(retVal);
    }

    @Override
    public ExcanRole update(ExcanRole entity) {
        ExcanRoleJpo jpoToUpdate = new ExcanRoleJpo(entity);
        Set<ExcanPermissionJpo> excanPermissionJpos = entity.getPermissions().stream().map(i -> new ExcanPermissionJpo(i)).collect(Collectors.toSet());
        jpoToUpdate.setPermissions(excanPermissionJpos);
        ExcanRoleJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public ExcanRole create(ExcanRole entity) {
        ExcanRoleJpo jpoToSave = new ExcanRoleJpo(entity);
        Set<ExcanPermissionJpo> excanPermissionJpos = entity.getPermissions().stream().map(i -> new ExcanPermissionJpo(i)).collect(Collectors.toSet());
        jpoToSave.setPermissions(excanPermissionJpos);
        ExcanRoleJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public ExcanRole findById(int id) {
        ExcanRoleJpo savedJpo = this.repository.findById(id).orElse(null);
        if(savedJpo != null){
            return savedJpo.toDomain();
        }
        return null;
    }

    @Override
    public List<ExcanRole> findByName(ERole name) {
        List<ExcanRoleJpo> jpos = repository.findByName(name);
        return jpos.stream().map(ExcanRoleJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public ExcanRole findByNameString(String name) {
        ExcanRoleJpo jpos = repository.findByName(name).get();
        return jpos.toDomain();
    }

    @Override
    public List<ExcanRole> findAll() {
        List<ExcanRoleJpo> jpos = repository.findAll();
        return jpos.stream().map(ExcanRoleJpo::toDomain).collect(Collectors.toList());
    }
}
