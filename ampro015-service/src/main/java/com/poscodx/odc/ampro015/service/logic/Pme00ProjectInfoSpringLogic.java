package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00ProjectInfoLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00ProjectInfoSpringLogic extends Pme00ProjectInfoLogic {
    public Pme00ProjectInfoSpringLogic(Pme00ProjectInfoStore store) {super(store);}
}
