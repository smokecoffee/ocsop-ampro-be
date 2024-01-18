package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public List<IssueManagement> getAll() {
        List<IssueManagement> getAllIssue = serviceLifecycle.requestIssueManagementService().retrieveAll();
        return getAllIssue;
    }

}
