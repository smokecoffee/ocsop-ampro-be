package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00PermissionLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00PermissionStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00PermissionSpringLogic extends Pme00PermissionLogic
{
    public Pme00PermissionSpringLogic(Pme00PermissionStore store) {
        super(store);
    }
}
