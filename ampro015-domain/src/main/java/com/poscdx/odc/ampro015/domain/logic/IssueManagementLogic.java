package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
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
    public List<IssueManagement> retrieve(int seq, String site) {
        return this.store.retrieve(seq,site);
    }

    @Override
    public List<IssueManagement> retrieveAll() {
        return this.store.retrieveAll();
    }

    @Override
    public IssueManagementResponse modify(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.update(issueManagement);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been updated");
        return response;
    }


    @Override
    public IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement newIssueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.create(newIssueManagement);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been created");
        return response;
    }

    @Override
    public IssueManagementResponse remove(IssueManagementId seq) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.delete(seq);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("This issue has been deleted");
        return response;
    }

    @Override
    public List<IssueManagement> findIssueInfo(String contents) {
        IssueManagementResponse response = new IssueManagementResponse();
        List<IssueManagement> list = this.store.findIssueInfo(contents);
        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return list;
    }

    @Override
    public List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer) {
        IssueManagementResponse response = new IssueManagementResponse();
        List<IssueManagement> list = this.store.searchIssue(site,module,division_flag,applied_period_flag,accept_flag,request_confirm,requester,contents,contents_kr,developer);
        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return list;
    }
}
