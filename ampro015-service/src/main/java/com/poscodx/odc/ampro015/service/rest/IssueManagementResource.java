package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue-management")
public class IssueManagementResource {

    @Autowired
    private ServiceLifecycler serviceLifecycle;

    public IssueManagementResource(ServiceLifecycler serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    @CrossOrigin
    @PostMapping("/insert")
    public IssueManagementResponse insertIssue(@RequestBody IssueManagement newIssueManagement){
        return this.serviceLifecycle.requestIssueManagementService().create(serviceLifecycle,newIssueManagement);
    }

    @PutMapping
    public IssueManagementResponse updateIssue(@RequestBody IssueManagement issueManagement){
        return this.serviceLifecycle.requestIssueManagementService().modify(serviceLifecycle,issueManagement);
    }

    @DeleteMapping
    public IssueManagementResponse deleteIssue(@RequestBody IssueManagementId seq){
        return this.serviceLifecycle.requestIssueManagementService().remove(seq);
    }

    @GetMapping("/")
    public List<IssueManagement> getAll() {
        List<IssueManagement> getAllIssue = serviceLifecycle.requestIssueManagementService().retrieveAll();
        return getAllIssue;
    }

    @GetMapping("/getbyseqandsite")
    public List<IssueManagement> getBySeqAndSite(@RequestParam int seq,@RequestParam String site) {
        List<IssueManagement> getSeqAndSite = serviceLifecycle.requestIssueManagementService().retrieve(seq,site);
        return getSeqAndSite;
    }

}
