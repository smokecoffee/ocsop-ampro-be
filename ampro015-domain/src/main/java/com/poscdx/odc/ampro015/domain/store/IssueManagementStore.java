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

    void delete(IssueManagementId id);


    List<Object[]> findIssueInfo(String contents, String site, List<String> modules, List<String> division_flag, String applied_period_flag,
                                 String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer,
                                 Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate,
                                 Date requestToEndDate, Pageable pageable);


    List<Object[]> search(String content, List<String> site, List<String> module, boolean module_check, boolean division_check, List<String> division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer, Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate,
                          Date requestToEndDate, Pageable pageable);

    int totalIssue(String content, List<String> site, List<String> module, boolean module_check, boolean division_check, List<String> division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer, Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate,
                   Date requestToEndDate);

    int maxSeq();

}
