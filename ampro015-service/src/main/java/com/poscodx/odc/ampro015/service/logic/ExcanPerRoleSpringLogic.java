package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.ExcanPerRoleLogic;
import com.poscdx.odc.ampro015.domain.store.ExcanPerRoleStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExcanPerRoleSpringLogic extends ExcanPerRoleLogic
{
    public ExcanPerRoleSpringLogic(ExcanPerRoleStore store) {
        super(store);
    }
}
