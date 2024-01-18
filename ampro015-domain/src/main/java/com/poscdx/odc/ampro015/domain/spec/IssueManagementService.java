package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;

public interface IssueManagementService {

    IssueManagement retrieve(int id);

    List<IssueManagement> retrieveAll();

    IssueManagementResponse modify(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement);

    IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement);

    IssueManagementResponse remove(ServiceLifecycle serviceLifecycle,int id);

    List<IssueManagement> findIssueInfo(ServiceLifecycle serviceLifecycle,String content);

    List<IssueManagement> findIssueInfo(String content);
}
