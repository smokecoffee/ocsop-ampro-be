package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.RequestParam;

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
                                        String fromRegistrationStartDate,String toRegistrationEndDate,
                                        String fromRequestStartDate,String toRequestEndDate);

    List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer);

    List<IssueManagementDto> findIssueDto(@RequestParam String contents,@RequestParam String site, @RequestParam String module,
                                          @RequestParam String division_flag, @RequestParam String applied_period_flag,
                                          @RequestParam String accept_flag, @RequestParam String requester_confirm,
                                          @RequestParam String requester, @RequestParam String contents_kr, @RequestParam String developer,
                                          @RequestParam Date registration_date, @RequestParam String request_date);
}
