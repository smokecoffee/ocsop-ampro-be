package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00MemberLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00MemberStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00MemberSpringLogic extends Pme00MemberLogic {
    public Pme00MemberSpringLogic(Pme00MemberStore store) {
        super(store);
    }
}
