package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface Pme00IssueManagementService
 * @author 202307_Phat
 * @since 2024-01-23
 */
public interface Pme00IssueManagementService {
    /**
     * Interface Retrieve issue by site and seq
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    List<IssueManagement> retrieve(int seq, String site);
    /**
     * Interface Retrieve issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    List<IssueManagement> retrieveAll();
    /**
     * Interface Modified issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    IssueManagementResponse modify(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement);
    /**
     * Interface Create issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    IssueManagementResponse create(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement);
    /**
     * Interface Remove issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    IssueManagementResponse remove(IssueManagementId seq);
    /**
     * Interface find issue info by param return map
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    Map<String, Object> findIssueInfo(String contents, String site, String module, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester,
                                      String contents_kr, String developer, String fromRegistrationStartDate, String toRegistrationEndDate, String fromRequestStartDate, String toRequestEndDate, int pageNo, int pageSize) throws ParseException;
    /**
     * Interface find issue info by param return list
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer);
    /**
     * Interface find issue info dto by param return list
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    List<IssueManagementDto> findIssueDto(@RequestParam String contents, @RequestParam String site, @RequestParam String module,
                                          @RequestParam String division_flag, @RequestParam String applied_period_flag,
                                          @RequestParam String accept_flag, @RequestParam String requester_confirm,
                                          @RequestParam String requester, @RequestParam String contents_kr, @RequestParam String developer,
                                          @RequestParam Date registration_date, @RequestParam Date request_date);
}
