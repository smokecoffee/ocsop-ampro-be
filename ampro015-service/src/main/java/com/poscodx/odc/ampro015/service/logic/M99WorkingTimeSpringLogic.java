package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M99WorkingTimeLogic;
import com.poscdx.odc.ampro015.domain.store.M99WorkingTimeStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M99WorkingTimeSpringLogic extends M99WorkingTimeLogic {

    public M99WorkingTimeSpringLogic(M99WorkingTimeStore store){
        super(store);
    }
}
