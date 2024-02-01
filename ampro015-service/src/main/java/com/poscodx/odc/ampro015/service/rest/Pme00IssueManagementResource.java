package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Router API for issue management Class
 *
 * @author 202307_Phat
 * @since 2024-01-23
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/issue-management")
@RequiredArgsConstructor
public class Pme00IssueManagementResource {

    @Autowired
    private ServiceLifecycle serviceLifecycle;


    /**
     * Get List Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    public Pme00IssueManagementResource(ServiceLifecycle serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    /**
     * Insert Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADD_ISSUE')")
    public IssueManagementResponse insertIssue(@RequestParam("data") String dtoString,
                                               @RequestParam("fileName") MultipartFile fileUpload) {
        IssueManagement issueManagement = IssueManagement.fromJson(dtoString);
        return this.serviceLifecycle.requestPme00IssueManagementService().create(serviceLifecycle, issueManagement, fileUpload);
    }

    /**
     * Update Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('UPDATE_ISSUE,UPDATE_ISSUE_OWNER')")
    public IssueManagementResponse updateIssue(@RequestParam("data") String dtoString,
                                               @RequestParam("fileName") MultipartFile fileUpload) {
        IssueManagement issueManagement = IssueManagement.fromJson(dtoString);
        return this.serviceLifecycle.requestPme00IssueManagementService().modify(serviceLifecycle, issueManagement, fileUpload);
    }

    /**
     * Delete Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('DELETE_ISSUE,DELETE_ISSUE_OWNER')")
    public IssueManagementResponse deleteIssue(@RequestBody IssueManagementId seq) {
        return this.serviceLifecycle.requestPme00IssueManagementService().remove(seq, serviceLifecycle);
    }

    /**
     * Get All Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('GET_ISSUE,GET_ISSUE_OWNER')")
    public List<IssueManagement> getAll() {
        return this.serviceLifecycle.requestPme00IssueManagementService().retrieveAll();
    }

    /**
     * Update Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @GetMapping("/getbyseqandsite")
    @PreAuthorize("hasAnyAuthority('GET_ISSUE,GET_ISSUE_OWNER')")
    public List<IssueManagement> getBySeqAndSite(@RequestParam int seq, @RequestParam String site) {
        return this.serviceLifecycle.requestPme00IssueManagementService().retrieve(seq, site);
    }


    /**
     * Search Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @GetMapping(path = "/search")
    @PreAuthorize("hasAnyAuthority('GET_ISSUE,GET_ISSUE_OWNER')")
    public Map<String, Object> search(@RequestParam(required = false) String contents,
                                      @RequestParam(required = false) String site,
                                      @RequestParam(required = false) String module,
                                      @RequestParam(required = false) String division_flag,
                                      @RequestParam(required = false) String applied_period_flag,
                                      @RequestParam(required = false) String accept_flag,
                                      @RequestParam(required = false) String requester_confirm,
                                      @RequestParam(required = false) String requester,
                                      @RequestParam(required = false) String requester_id,
                                      @RequestParam(required = false) String contents_kr,
                                      @RequestParam(required = false) String developer,
                                      @RequestParam(required = false) String fromRegistrationStartDate,
                                      @RequestParam(required = false) String toRegistrationEndDate,
                                      @RequestParam(required = false) String fromRequestStartDate,
                                      @RequestParam(required = false) String toRequestEndDate,
                                      @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                      @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize
    ) throws ParseException {
        return this.serviceLifecycle.requestPme00IssueManagementService()
                                    .findIssueInfo(contents, site, module, division_flag,
                                                    applied_period_flag, accept_flag, requester_confirm, requester, requester_id,
                                                    contents_kr, developer, fromRegistrationStartDate,
                                                    toRegistrationEndDate, fromRequestStartDate, toRequestEndDate, pageNo, pageSize);
    }

}
