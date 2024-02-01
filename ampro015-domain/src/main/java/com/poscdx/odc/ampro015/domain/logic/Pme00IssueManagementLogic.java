package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00IssueManagementService;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

import java.util.*;

public class Pme00IssueManagementLogic implements Pme00IssueManagementService {

    private final IssueManagementStore store;

    public Pme00IssueManagementLogic(IssueManagementStore store) {
        this.store = store;
    }

    @Override
    public List<IssueManagement> retrieve(int seq, String site) {
        return this.store.retrieve(seq, site);
    }

    @Override
    public List<IssueManagement> retrieveAll() {
        return this.store.retrieveAll();
    }


    /**
     * Modify issue management
     *
     * @param //ServiceLifecycle serviceLifecycle,IssueManagement modifyIssueManagement
     * @param //dto
     * @return Issue management response
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public IssueManagementResponse modify(ServiceLifecycle serviceLifecycle, IssueManagement issueManagement, MultipartFile fileUpload) {
        if (fileUpload != null || issueManagement.getFileName().isEmpty()) {
            List<String> fileName = new ArrayList<>();
            List<IssueManagement> issueList = store.retrieve(issueManagement.getSeq(), issueManagement.getSite());
            for (IssueManagement issue : issueList) {
                fileName.add(issue.getFileName());
            }
            serviceLifecycle.requestLevel2Service()
                    .removeFile(Utils.UPLOAD_BUCKET, "Issue", fileName);
        }
        IssueManagementResponse response = new IssueManagementResponse();
        store.update(issueManagement);
        if (fileUpload != null) {
            String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Issue", fileUpload);
            result.contains("Issue");
        }
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been updated");
        return response;
    }

    /**
     * Create issue management
     *
     * @param //ServiceLifecycle serviceLifecycle,IssueManagement newIssueManagement
     * @param //dto
     * @return Issue management response
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public IssueManagementResponse create(ServiceLifecycle serviceLifecycle, IssueManagement newIssueManagement, MultipartFile fileUpload) {
        IssueManagementResponse response = new IssueManagementResponse();
        int seq = store.maxSeq() + 1;
        newIssueManagement.setSeq(seq);
        if (fileUpload != null) {
            String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Issue", fileUpload);
            result.contains("Issue");
        }
        store.create(newIssueManagement);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been created");
        return response;
    }

    /**
     * Remove issue management
     *
     * @param //IssueManagementId seq
     * @param //dto
     * @return Issue management response
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public IssueManagementResponse remove(IssueManagementId seq, ServiceLifecycle serviceLifecycle) {
        IssueManagementResponse response = new IssueManagementResponse();
        List<String> fileName = new ArrayList<>();
        List<IssueManagement> issueList = store.retrieve(seq.getSeq(), seq.getSite());
        if (!issueList.isEmpty()) {
            for (IssueManagement issue : issueList) {
                fileName.add(issue.getFileName());
            }
            serviceLifecycle.requestLevel2Service()
                    .removeFile(Utils.UPLOAD_BUCKET, "Issue", fileName);
            store.delete(seq);
        }
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("This issue has been deleted");
        return response;
    }


    /**
     * Find issue management
     *
     * @param //IssueManagementId seq
     * @param //dto
     * @return Map response
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public Map<String, Object> findIssueInfo(String content, String site, List<String> module, List<String> division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer, Date fromRegistrationStartDate, Date toRegistrationEndDate, Date fromRequestStartDate, Date toRequestEndDate, int pageNo, int pageSize) throws ParseException {
        Pageable pageable;
        if (pageSize == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("status"));
        }
        boolean module_check = (module == null || module.isEmpty());
        if (module == null) {
            module = new ArrayList<>();
        }
        if (module.isEmpty()) {
            module.add("");
        }
        boolean division_check = (division_flag == null || division_flag.isEmpty());
        if (division_flag == null) {
            division_flag = new ArrayList<>();
        }
        if (division_flag.isEmpty()) {
            division_flag.add("");
        }
        List<Object[]> list = this.store.search(content, site, module, module_check, division_check, division_flag, applied_period_flag, accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, fromRegistrationStartDate, toRegistrationEndDate, fromRequestStartDate, toRequestEndDate, pageable);
        List<IssueManagement> issueManagementDtoList = new ArrayList<>();
        for (Object[] objects : list) {
            issueManagementDtoList.add(new IssueManagement(objects));
        }
        int total = store.totalIssue(content, site, module, module_check, division_check, division_flag, applied_period_flag, accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, fromRegistrationStartDate,
                toRegistrationEndDate, fromRequestStartDate, toRequestEndDate);
        Map<String, Object> responses = new HashMap<>();
        responses.put("status", HttpStatus.FOUND.value());
        responses.put("message", "OK");
        responses.put("total", total);
        responses.put("data", issueManagementDtoList);
        return responses;
    }

}
