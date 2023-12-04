package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00AnnouncementLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00AnnouncementStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00AnnouncementSpringLogic extends Pme00AnnouncementLogic {
    public Pme00AnnouncementSpringLogic(Pme00AnnouncementStore store) {
        super(store);
    }
}
