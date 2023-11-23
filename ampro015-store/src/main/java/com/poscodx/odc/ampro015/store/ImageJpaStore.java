package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import com.poscodx.odc.ampro015.store.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageJpaStore implements ImageStore {

    @Autowired ImageRepository imageRepository;

    @Override
    public Image add(Image image) {
        ImageJpo imageJpo = new ImageJpo(image);
        ImageJpo addImage = imageRepository.save(imageJpo);
        return addImage.toDomain();
    }
}
