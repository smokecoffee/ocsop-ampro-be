package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.LogoutAccessTokenLogic;
import com.poscdx.odc.ampro015.domain.store.LogoutAccessTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LogoutAccessTokenSpringLogic extends LogoutAccessTokenLogic
{
    public LogoutAccessTokenSpringLogic(LogoutAccessTokenStore store) {
        super(store);
    }
}
