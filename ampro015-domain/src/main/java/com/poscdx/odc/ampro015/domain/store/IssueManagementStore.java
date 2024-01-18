package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;

import java.util.List;

public interface IssueManagementStore {
    List<IssueManagement> retrieve(int seq, String site);

    List<IssueManagement> retrieveAll();

    IssueManagement update(IssueManagement entity);

    IssueManagement create(IssueManagement entity);

    void delete(IssueManagementId seq);

    List<IssueManagement> findIssueInfo(String content);
}
