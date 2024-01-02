package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00RoleUserLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleUserStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00RoleUserSpringLogic extends Pme00RoleUserLogic {
    public Pme00RoleUserSpringLogic(Pme00RoleUserStore store) {
        super(store);
    }
}
