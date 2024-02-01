package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00WorkingTimeLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00WorkingTimeStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00WorkingTimeSpringLogic extends Pme00WorkingTimeLogic {

    public Pme00WorkingTimeSpringLogic(Pme00WorkingTimeStore store){
        super(store);
    }
}
