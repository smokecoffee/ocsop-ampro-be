package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.S91MenuLogic;
import com.poscdx.odc.ampro015.domain.store.S91MenuStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class S91MenuSpringLogic extends S91MenuLogic {
    public S91MenuSpringLogic(S91MenuStore store) {
        super(store);
    }
}
