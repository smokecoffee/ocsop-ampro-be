package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Router API for issue management Class
 *
 * @author 202307_Phat
 * @since 2024-01-23
 */
@RestController
@RequestMapping("/issue-management")
@RequiredArgsConstructor
public class Pme00IssueManagementResource {

    @Value("${minio.bucketName}")
    private String bucketName;
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
    @CrossOrigin
    @PostMapping
    public IssueManagementResponse insertIssue(@RequestBody IssueManagement newIssueManagement) {
        return this.serviceLifecycle.requestPme00IssueManagementService().create(serviceLifecycle, newIssueManagement);
    }

    /**
     * Update Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @CrossOrigin
    @PutMapping
    public IssueManagementResponse updateIssue(@RequestBody IssueManagement issueManagement) {
        return this.serviceLifecycle.requestPme00IssueManagementService().modify(serviceLifecycle, issueManagement);
    }

    /**
     * Delete Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @CrossOrigin
    @DeleteMapping
    public IssueManagementResponse deleteIssue(@RequestBody IssueManagementId seq) {
        return this.serviceLifecycle.requestPme00IssueManagementService().remove(seq);
    }

    /**
     * Get All Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @CrossOrigin
    @GetMapping
    public List<IssueManagement> getAll() {
        List<IssueManagement> getAllIssue = serviceLifecycle.requestPme00IssueManagementService().retrieveAll();
        return getAllIssue;
    }

    /**
     * Update Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @CrossOrigin
    @GetMapping("/getbyseqandsite")
    public List<IssueManagement> getBySeqAndSite(@RequestParam int seq, @RequestParam String site) {
        List<IssueManagement> getSeqAndSite = serviceLifecycle.requestPme00IssueManagementService().retrieve(seq, site);
        return getSeqAndSite;
    }


    /**
     * Search Issue Management function
     *
     * @return Pme00IssueManagementResponse
     * @author 202307_Phat
     * @since 2024-01-23
     */
    @CrossOrigin
    @GetMapping(path = "/search")
    public Map<String, Object> search(@RequestParam(required = false) String contents,
                                      @RequestParam(required = false) String site,
                                      @RequestParam(required = false) String module,
                                      @RequestParam(required = false) String division_flag,
                                      @RequestParam(required = false) String applied_period_flag,
                                      @RequestParam(required = false) String accept_flag,
                                      @RequestParam(required = false) String requester_confirm,
                                      @RequestParam(required = false) String requester,
                                      @RequestParam(required = false) String contents_kr,
                                      @RequestParam(required = false) String developer,
                                      @RequestParam(required = false) String fromRegistrationStartDate,
                                      @RequestParam(required = false) String toRegistrationEndDate,
                                      @RequestParam(required = false) String fromRequestStartDate,
                                      @RequestParam(required = false) String toRequestEndDate,
                                      @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                      @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize
    ) throws ParseException {
        System.out.println(fromRegistrationStartDate);
        return this.serviceLifecycle.requestPme00IssueManagementService().findIssueInfo(contents, site, module, division_flag,
                applied_period_flag, accept_flag, requester_confirm, requester, contents_kr, developer, fromRegistrationStartDate,
                toRegistrationEndDate, fromRequestStartDate, toRequestEndDate, pageNo, pageSize);
    }

}
