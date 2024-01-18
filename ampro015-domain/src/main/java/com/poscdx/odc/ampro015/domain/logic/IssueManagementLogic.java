package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.IssueManagementService;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import org.springframework.http.HttpStatus;

import java.util.List;

public class IssueManagementLogic implements IssueManagementService {

    private final IssueManagementStore store;

    public IssueManagementLogic(IssueManagementStore store) {
        this.store = store;
    }

    @Override
    public IssueManagement retrieve(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<IssueManagement> retrieveAll() {
        return this.store.retrieveAll();
    }

    @Override
    public IssueManagementResponse modify(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement) {
        return null;
    }

    @Override
    public IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement newIssueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        //serviceLifecycle.requestIssueManagementService().create(serviceLifecycle, newIssueManagement);
        store.create(newIssueManagement);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been created");
        return response;
    }

    @Override
    public IssueManagementResponse remove(ServiceLifecycle serviceLifecycle, int id) {
        return null;
    }


    @Override
    public List<IssueManagement> findIssueInfo(ServiceLifecycle serviceLifecycle, String content) {
        return null;
    }

    @Override
    public List<IssueManagement> findIssueInfo(String content) {
        return null;
    }
}
