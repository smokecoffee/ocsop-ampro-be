package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.multipart.MultipartFile;

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
    IssueManagementResponse modify(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement, MultipartFile fileUpload);
    /**
     * Interface Create issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    IssueManagementResponse create(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement, MultipartFile fileUpload);
    /**
     * Interface Remove issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    IssueManagementResponse remove(IssueManagementId seq, ServiceLifecycle serviceLifecycle);
    /**
     * Interface search issue
     * @author 202307_Phat
     *  * @since 2024-01-23
     */
    Map<String, Object> search(String content, String site, List<String> module,List<String> division_flag, String applied_period_flag,
                               String accept_flag,String requester_confirm,String requester,String requester_id,String contents_kr,
                               String developer,Date fromRegistrationStartDate, Date toRegistrationEndDate, Date fromRequestStartDate,
                               Date toRequestEndDate, int pageNo, int pageSize) throws ParseException;
}
