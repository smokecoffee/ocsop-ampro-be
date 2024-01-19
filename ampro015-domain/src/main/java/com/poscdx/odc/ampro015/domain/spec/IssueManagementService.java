package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.Date;
import java.util.List;

public interface IssueManagementService {

    List<IssueManagement> retrieve(int seq, String site);

    List<IssueManagement> retrieveAll();

    IssueManagementResponse modify(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement);

    IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement);

    IssueManagementResponse remove(IssueManagementId seq);

    List<IssueManagement> findIssueInfo(String contents, String site, String module, String division_flag,
                                        String applied_period_flag, String accept_flag, String requester_confirm,
                                        String requester, String contents_kr, String developer,
                                        Date registration_date, String request_date);

    List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer);
}
