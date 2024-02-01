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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
   // @PreAuthorize("hasAnyAuthority('ADD_ISSUE')")
    public IssueManagementResponse insertIssue(
                                                   @RequestParam(required = false, name = "registrationDate")  String registrationDate,
                                                   @RequestParam(required = false, name = "requestDate") String requestDate,
                                                   @RequestParam(required = false, name = "requester") String requester,
                                                   @RequestParam(required = false, name = "site") String site,
                                                   @RequestParam(required = false, name = "module") String module,
                                                   @RequestParam(required = false, name = "menu") String menu,
                                                   @RequestParam(required = false, name = "screenName") String screenName,
                                                   @RequestParam(required = false, name = "divisionFlag") String divisionFlag,
                                                   @RequestParam(required = false, name = "appliedPeriodFlag") String appliedPeriodFlag,
                                                   @RequestParam(required = false, name = "contents") String contents,
                                                   @RequestParam(required = false, name = "contentsKR") String contentsKR,
                                                   @RequestParam(required = false, name = "developer") String developer,
                                                   @RequestParam(required = false, name = "fileName") String fileName,
                                                   @RequestParam(required = false, name = "acceptFlag") String acceptFlag,
                                                   @RequestParam(required = false, name = "status") String status,
                                                   @RequestParam(required = false, name = "developComments") String developComments,
                                                   @RequestParam(required = false, name = "developFromDate") String developFromDate,
                                                   @RequestParam(required = false, name = "developToDate") String developToDate,
                                                   @RequestParam(required = false, name = "categoryFlag") String categoryFlag,
                                                   @RequestParam(required = false, name = "requesterConfirm") String requesterConfirm,
                                                   @RequestParam(required = false, name = "finalConfirmDate") String finalConfirmDate,
                                                   @RequestParam(required = false, name = "requesterId") String requesterId,
                                                   @RequestParam(value = "file", required = false) MultipartFile fileUpload) throws ParseException {
        DateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        Date _requestDate = (Date) formatter.parse(requestDate);
        Date _registrationDate = (Date) formatter.parse(registrationDate);
        Date _developFromDate = (Date) formatter.parse(developFromDate);
        Date _developToDate = (Date) formatter.parse(developToDate);
        Date _finalConfirmDate = (Date) formatter.parse(finalConfirmDate);


        IssueManagement issueManagement = new IssueManagement();
        issueManagement.setSeq(999);
        issueManagement.setRegistrationDate(_registrationDate);
        issueManagement.setRequestDate(_requestDate);
        issueManagement.setRequester(requester);
        issueManagement.setSite(site);
        issueManagement.setModule(module);
        issueManagement.setMenu(menu);
        issueManagement.setScreenName(screenName);
        issueManagement.setDivisionFlag(divisionFlag);
        issueManagement.setAppliedPeriodFlag(appliedPeriodFlag);
        issueManagement.setContents(contents);
        issueManagement.setContentsKR(contentsKR);
        issueManagement.setDeveloper(developer);
        issueManagement.setFileName(fileName);
        issueManagement.setAcceptFlag(acceptFlag);
        issueManagement.setStatus(status);
        issueManagement.setDevelopComments(developComments);
        issueManagement.setDevelopFromDate(_developFromDate);
        issueManagement.setDevelopToDate(_developToDate);
        issueManagement.setCategoryFlag(categoryFlag);
        issueManagement.setRequesterConfirm(requesterConfirm);
        issueManagement.setFinalConfirmDate(_finalConfirmDate);
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
    public IssueManagementResponse updateIssue(  @RequestParam(required = false, name = "registrationDate")  String registrationDate,
                                                 @RequestParam(required = false, name = "requestDate") String requestDate,
                                                 @RequestParam(required = false, name = "requester") String requester,
                                                 @RequestParam(required = false, name = "site") String site,
                                                 @RequestParam(required = false, name = "module") String module,
                                                 @RequestParam(required = false, name = "menu") String menu,
                                                 @RequestParam(required = false, name = "screenName") String screenName,
                                                 @RequestParam(required = false, name = "divisionFlag") String divisionFlag,
                                                 @RequestParam(required = false, name = "appliedPeriodFlag") String appliedPeriodFlag,
                                                 @RequestParam(required = false, name = "contents") String contents,
                                                 @RequestParam(required = false, name = "contentsKR") String contentsKR,
                                                 @RequestParam(required = false, name = "developer") String developer,
                                                 @RequestParam(required = false, name = "fileName") String fileName,
                                                 @RequestParam(required = false, name = "acceptFlag") String acceptFlag,
                                                 @RequestParam(required = false, name = "status") String status,
                                                 @RequestParam(required = false, name = "developComments") String developComments,
                                                 @RequestParam(required = false, name = "developFromDate") String developFromDate,
                                                 @RequestParam(required = false, name = "developToDate") String developToDate,
                                                 @RequestParam(required = false, name = "categoryFlag") String categoryFlag,
                                                 @RequestParam(required = false, name = "requesterConfirm") String requesterConfirm,
                                                 @RequestParam(required = false, name = "finalConfirmDate") String finalConfirmDate,
                                                 @RequestParam(required = false, name = "requesterId") String requesterId,
                                                 @RequestParam(value = "file", required = false) MultipartFile fileUpload) throws ParseException {
        DateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        Date _requestDate = (Date) formatter.parse(requestDate);
        Date _registrationDate = (Date) formatter.parse(registrationDate);
        Date _developFromDate = (Date) formatter.parse(developFromDate);
        Date _developToDate = (Date) formatter.parse(developToDate);
        Date _finalConfirmDate = (Date) formatter.parse(finalConfirmDate);


        IssueManagement issueManagement = new IssueManagement();

        issueManagement.setRegistrationDate(_registrationDate);
        issueManagement.setRequestDate(_requestDate);
        issueManagement.setRequester(requester);
        issueManagement.setSite(site);
        issueManagement.setModule(module);
        issueManagement.setMenu(menu);
        issueManagement.setScreenName(screenName);
        issueManagement.setDivisionFlag(divisionFlag);
        issueManagement.setAppliedPeriodFlag(appliedPeriodFlag);
        issueManagement.setContents(contents);
        issueManagement.setContentsKR(contentsKR);
        issueManagement.setDeveloper(developer);
        issueManagement.setFileName(fileName);
        issueManagement.setAcceptFlag(acceptFlag);
        issueManagement.setStatus(status);
        issueManagement.setDevelopComments(developComments);
        issueManagement.setDevelopFromDate(_developFromDate);
        issueManagement.setDevelopToDate(_developToDate);
        issueManagement.setCategoryFlag(categoryFlag);
        issueManagement.setRequesterConfirm(requesterConfirm);
        issueManagement.setFinalConfirmDate(_finalConfirmDate);
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
   // @PreAuthorize("hasAnyAuthority('GET_ISSUE,GET_ISSUE_OWNER')")
    public Map<String, Object> search(@RequestParam(required = false) String contents,
                                      @RequestParam(required = false) List<String> site,
                                      @RequestParam(required = false) List<String>  module,
                                      @RequestParam(required = false) List<String>  division_flag,
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
                .search(contents, site, module, division_flag,
                                                    applied_period_flag, accept_flag, requester_confirm, requester, requester_id,
                                                    contents_kr, developer, fromRegistrationStartDate,
                                                    toRegistrationEndDate, fromRequestStartDate, toRequestEndDate, pageNo, pageSize);
    }

}
