package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueManagementStore {
    List<IssueManagement> retrieve(int seq, String site);

    List<IssueManagement> retrieveAll();

    IssueManagement update(IssueManagement entity);

    IssueManagement create(IssueManagement entity);

    void delete(IssueManagementId seq);

    List<IssueManagement> findIssueInfo(String contents);

    List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer);
}
