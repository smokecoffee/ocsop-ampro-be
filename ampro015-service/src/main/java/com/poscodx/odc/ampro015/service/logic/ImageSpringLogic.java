package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.ImageLogic;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ImageSpringLogic extends ImageLogic {
    public ImageSpringLogic(ImageStore store) {
        super(store);
    }
}