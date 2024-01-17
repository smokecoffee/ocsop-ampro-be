package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.IssueManagementLogic;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IssueManagementSpringLogic extends IssueManagementLogic {
    public IssueManagementSpringLogic(IssueManagementStore store){ super(store);}
}
