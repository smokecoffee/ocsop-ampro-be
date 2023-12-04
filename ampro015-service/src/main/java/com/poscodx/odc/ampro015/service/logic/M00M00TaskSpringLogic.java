package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M00TaskLogic;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M00M00TaskSpringLogic extends M00TaskLogic {
    public M00M00TaskSpringLogic(M00TaskStore store) {
        super(store);
    }
}
