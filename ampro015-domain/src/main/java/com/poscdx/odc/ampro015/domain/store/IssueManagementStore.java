package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

public interface IssueManagementStore {
    List<IssueManagement> retrieve(int seq, String site);

    List<IssueManagement> retrieveAll();

    IssueManagement update(IssueManagement entity);

    IssueManagement create(IssueManagement entity);

    void delete(IssueManagementId seq);


    List<Object[]> findIssueInfo(String contents, String site, String modules, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String contents_kr, String developer,
                                 Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate, Date requestToEndDate, Pageable pageable);

    List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer);

    List<Object[]> findIssueManagementDto(String contents, String site, String modules, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String contents_kr, String developer, Date registration_date, Date request_date);

    int findIssueReport(String contents, String site, String modules, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String contents_kr, String developer,
                        Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate, Date requestToEndDate);

}
