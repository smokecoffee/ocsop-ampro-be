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
    public Asset retrieve(int id) {
        Optional<AssetJpo> retVal = this.repository.findById(id);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<Asset> retrieveAll() {
        return AssetJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Asset update(Asset entity) {
        AssetJpo jpoToUpdate = new AssetJpo(entity);
        AssetJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Asset create(Asset entity) {
        return this.repository.save(new AssetJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Asset> retrieveByOwnerAndStatus(String owner, int status) {
        Iterable<AssetJpo> list = this.repository.findByOwnerContainingAndStatusAndDeleteAtIsNull(owner, status);
        return AssetJpo.toDomains(list);
    }

    @Override
    public Asset findByToken(String token) {
        AssetJpo findAssetJpo = this.repository.findByToken(token);
        return findAssetJpo.toDomain();
    }

    @Override
    public List<Object[]> findByAssetAndOwnerAndStatus(int assetId, String emplId, int status) {
        return this.repository.findByAssetAndOwnerAndStatus(assetId, emplId, status);
    }

}
