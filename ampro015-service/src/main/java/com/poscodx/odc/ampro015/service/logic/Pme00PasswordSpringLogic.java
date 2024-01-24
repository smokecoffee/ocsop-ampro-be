package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00PasswordLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00PasswordSpringLogic extends Pme00PasswordLogic
{
    public Pme00PasswordSpringLogic(Pme00PasswordStore store) {
        super(store);
    }
}
