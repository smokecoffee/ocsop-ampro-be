package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00PerRoleLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00PerRoleStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00PerRoleSpringLogic extends Pme00PerRoleLogic
{
    public Pme00PerRoleSpringLogic(Pme00PerRoleStore store) {
        super(store);
    }
}
