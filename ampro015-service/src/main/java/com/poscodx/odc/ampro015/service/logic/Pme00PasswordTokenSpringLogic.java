package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00PasswordTokenLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00PasswordTokenSpringLogic extends Pme00PasswordTokenLogic
{
    public Pme00PasswordTokenSpringLogic(Pme00PasswordTokenStore store) {
        super(store);
    }
}
