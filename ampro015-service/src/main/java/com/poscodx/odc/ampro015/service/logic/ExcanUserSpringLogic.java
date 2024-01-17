package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.ExcanUserLogic;
import com.poscdx.odc.ampro015.domain.store.ExcanUserStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExcanUserSpringLogic extends ExcanUserLogic
{
    public ExcanUserSpringLogic(ExcanUserStore store) {
        super(store);
    }
}
