package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscodx.odc.ampro015.store.jpo.AssetJpo;
import com.poscodx.odc.ampro015.store.repository.AssetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssetJpaStore implements AssetStore {

    private final AssetRepository repository;

    public AssetJpaStore(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asset retrieve(String id) {
        Optional<AssetJpo> retVal = this.repository.findById(Integer.valueOf(id));
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<Asset> retrieveAll() {
        return null;
    }

    @Override
    public List<Asset> retrieveByEmplNameAndStatus(String owner, int status) {
        Iterable<AssetJpo> test =  this.repository.findAllByOwnerAndStatus(owner,status);
        return AssetJpo.toDomains(test);
    }

    @Override
    public Asset update(Asset entity) {
        return null;
    }

    @Override
    public Asset create(Asset entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
