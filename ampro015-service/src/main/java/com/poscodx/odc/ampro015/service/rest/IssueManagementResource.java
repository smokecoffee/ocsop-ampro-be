package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementDto;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

pls comment code by rule
@RestController
@RequestMapping("/issue-management")
public class IssueManagementResource { <-- pls change to name same : Pme00IssueManagementResource

    @Autowired
    private ServiceLifecycler serviceLifecycle;


    pls comment code by rule
    public IssueManagementResource(ServiceLifecycler serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    pls comment code by rule
    @CrossOrigin
    @PostMapping("/insert") < remove insert
    public IssueManagementResponse insertIssue(@RequestBody IssueManagement newIssueManagement){
        return this.serviceLifecycle.requestIssueManagementService().create(serviceLifecycle,newIssueManagement);
    }

    pls comment code by rule
    @PutMapping
    public IssueManagementResponse updateIssue(@RequestBody IssueManagement issueManagement){
        return this.serviceLifecycle.requestIssueManagementService().modify(serviceLifecycle,issueManagement);
    }

    pls comment code by rule
    @DeleteMapping
    public IssueManagementResponse deleteIssue(@RequestBody IssueManagementId seq){
        return this.serviceLifecycle.requestIssueManagementService().remove(seq);
    }

    pls comment code by rule
    @GetMapping("/")
    public List<IssueManagement> getAll() {
        List<IssueManagement> getAllIssue = serviceLifecycle.requestIssueManagementService().retrieveAll();
        return getAllIssue;
    }

    pls comment code by rule
    @GetMapping("/getbyseqandsite")
    public List<IssueManagement> getBySeqAndSite(@RequestParam int seq,@RequestParam String site) {
        List<IssueManagement> getSeqAndSite = serviceLifecycle.requestIssueManagementService().retrieve(seq,site);
        return getSeqAndSite;
    }

    pls comment code by rule
    @PostMapping(path="/search") <-- pls check, if not use remove
    public List<IssueManagement> searchByConditions(@RequestBody IssueManagement issueManagement) throws ParseException {
        return this.serviceLifecycle.requestIssueManagementService().searchIssue(issueManagement.getSite(),
                issueManagement.getModule(), issueManagement.getDivisionFlag(), issueManagement.getAppliedPeriodFlag(),
                issueManagement.getAcceptFlag(), issueManagement.getRequesterConfirm(), issueManagement.getRequester(),
                issueManagement.getContents(), issueManagement.getContentsKR(), issueManagement.getDeveloper());
    }

    pls comment code by rule
    @GetMapping(path="/search-content") <-- replace search-content to search
    public List<IssueManagementDto> searchByContents(@RequestParam(required = false) String contents,
                                                  @RequestParam(required = false) String site,
                                                  @RequestParam(required = false) String module,
                                                  @RequestParam(required = false) String division_flag,
                                                  @RequestParam(required = false) String applied_period_flag,
                                                  @RequestParam(required = false) String accept_flag,
                                                  @RequestParam(required = false) String requester_confirm,
                                                  @RequestParam(required = false) String requester,
                                                  @RequestParam(required = false) String contents_kr,
                                                  @RequestParam(required = false) String developer,
                                                  @RequestParam(required = false)   String fromRegistrationStartDate,
                                                  @RequestParam(required = false)   String toRegistrationEndDate,
                                                  @RequestParam(required = false)   String fromRequestStartDate,
                                                  @RequestParam(required = false)   String toRequestEndDate
                                                  ) throws ParseException {
        return this.serviceLifecycle.requestIssueManagementService().findIssueInfo(contents, site, module, division_flag,
                applied_period_flag, accept_flag, requester_confirm, requester, contents_kr, developer, fromRegistrationStartDate,
                toRegistrationEndDate,fromRequestStartDate,toRequestEndDate);
    }

    pls comment code by rule
    @GetMapping(path="/find") <-- pls check, if not use remove
    public List<IssueManagementDto> findIssueDto(@RequestParam(required = false) String contents,
                                                 @RequestParam(required = false) String site,
                                                 @RequestParam(required = false) String module,
                                                 @RequestParam(required = false) String division_flag,
                                                 @RequestParam(required = false) String applied_period_flag,
                                                 @RequestParam(required = false) String accept_flag,
                                                 @RequestParam(required = false) String requester_confirm,
                                                 @RequestParam(required = false) String requester,
                                                 @RequestParam(required = false) String contents_kr,
                                                 @RequestParam(required = false) String developer,
                                                 @RequestParam(required = false) Date registration_date,
                                                 @RequestParam(required = false) String request_date){
        return this.serviceLifecycle.requestIssueManagementService().findIssueDto(contents, site, module, division_flag,
                applied_period_flag, accept_flag, requester_confirm, requester, contents_kr, developer, registration_date,
                request_date);
    }

}
