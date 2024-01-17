package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;
import com.poscdx.odc.ampro015.domain.store.ExcanUserStore;
import com.poscodx.odc.ampro015.store.jpo.ExcanUserJpo;
import com.poscodx.odc.ampro015.store.repository.ExcanUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExcanUserJpaStore
        implements ExcanUserStore {
    private final ExcanUserRepository repository;

    public ExcanUserJpaStore(ExcanUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExcanUser retrieve(String id) {
        Optional<ExcanUserJpo> retVal = this.repository.findById(id);
        return retVal.map(ExcanUserJpo::toDomain).orElse(null);
    }

    @Override
    public List<ExcanUser> retrieveAll() {
        List<ExcanUserJpo> retVal = this.repository.findAll();
        return ExcanUserJpo.toDomains(retVal);
    }

    @Override
    public ExcanUser update(ExcanUser entity) {
        ExcanUserJpo jpoToUpdate = new ExcanUserJpo(entity);
        ExcanUserJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public ExcanUser create(ExcanUser entity) {
        ExcanUserJpo jpoToSave = new ExcanUserJpo(entity);
        ExcanUserJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String userName) {
        return repository.existsByName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ExcanUser findById(String id) {
        ExcanUserJpo savedJpo = this.repository.findById(id).orElse(null);
        if (savedJpo != null) {
            return savedJpo.toDomain();
        }
        return null;
    }

    @Override
    public List<ExcanUser> findAll() {
        return this.repository.findAll().stream().map(ExcanUserJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ExcanUser> findAllByIdIn(List<String> id) {
        return this.repository.findAllByIdIn(id)
                .stream()
                .map(ExcanUserJpo::toDomain)
                .collect(Collectors.toList());
    }
}
