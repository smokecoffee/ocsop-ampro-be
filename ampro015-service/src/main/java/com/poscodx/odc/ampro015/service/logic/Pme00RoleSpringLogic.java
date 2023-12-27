package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00RoleLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00RoleSpringLogic extends Pme00RoleLogic {
    public Pme00RoleSpringLogic(Pme00RoleStore store) {
        super(store);
    }
}
