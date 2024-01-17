package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;

import java.util.List;

public interface IssueManagementService {

    IssueManagement retrieve(int id);

    List<IssueManagement> retrieveAll();

    IssueManagement modify(IssueManagement issueManagement);

    IssueManagement create(IssueManagement entity);

    void remove(int id);

    List<IssueManagement> findIssueInfo(String content);
}
