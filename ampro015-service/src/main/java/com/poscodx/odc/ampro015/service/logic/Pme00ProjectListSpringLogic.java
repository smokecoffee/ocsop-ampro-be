package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00ProjectListLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectListStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00ProjectListSpringLogic extends Pme00ProjectListLogic {
    public Pme00ProjectListSpringLogic(Pme00ProjectListStore store) {super(store);}
}