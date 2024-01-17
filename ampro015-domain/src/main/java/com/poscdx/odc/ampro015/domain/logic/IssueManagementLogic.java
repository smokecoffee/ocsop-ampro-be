package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.spec.IssueManagementService;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;

import java.util.List;

public class IssueManagementLogic implements IssueManagementService {

    private final IssueManagementStore store;

    public IssueManagementLogic(IssueManagementStore store) {
        this.store = store;
    }

    @Override
    public IssueManagement retrieve(int id) {
        return null;
    }

    @Override
    public List<IssueManagement> retrieveAll() {
        return null;
    }
}
