package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.ExcanAccessTokenLogic;
import com.poscdx.odc.ampro015.domain.store.ExcanAccessTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExcanAccessTokenSpringLogic extends ExcanAccessTokenLogic
{
    public ExcanAccessTokenSpringLogic(ExcanAccessTokenStore store) {
        super(store);
    }
}
