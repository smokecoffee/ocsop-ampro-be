package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.S91Menu;
import com.poscdx.odc.ampro015.domain.store.S91MenuStore;
import com.poscodx.odc.ampro015.store.jpo.S91MenuJpo;
import com.poscodx.odc.ampro015.store.repository.S91MenuRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class S91MenuJpaStore implements S91MenuStore {
    private final S91MenuRepository repository;

    public S91MenuJpaStore(S91MenuRepository repository) {
        this.repository = repository;
    }
    @Override
    public S91Menu retrieve(String id) {
        Optional<S91MenuJpo> retVal = this.repository.findById(id);
        return retVal.map(S91MenuJpo::toDomain).orElse(null);
    }

    @Override
    public List<S91Menu> retrieveAll() {
        return S91MenuJpo.toDomains(this.repository.findAll());
    }

    @Override
    public List<S91Menu> findMenuByPermission(List<String> permissionList) {
        return S91MenuJpo.toDomains(this.repository.findMenuByPermission(permissionList));
    }

}
