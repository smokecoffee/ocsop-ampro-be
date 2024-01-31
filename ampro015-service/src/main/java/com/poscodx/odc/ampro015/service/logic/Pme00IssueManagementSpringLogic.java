package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00IssueManagementLogic;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00IssueManagementSpringLogic extends Pme00IssueManagementLogic {
    public Pme00IssueManagementSpringLogic(IssueManagementStore store){ super(store);}
}
