package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import com.poscodx.odc.ampro015.store.repository.ImageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImageJpaStore implements ImageStore {
    private final ImageRepository repository;

    public ImageJpaStore(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Image retrieve(int id) {
        Optional<ImageJpo> retVal = this.repository.findById(id);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<Image> retrieveAll() {
        return ImageJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Image update(Image entity) {
        ImageJpo jpoToUpdate = new ImageJpo(entity);
        ImageJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Image create(Image entity) {
        return this.repository.save(new ImageJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
