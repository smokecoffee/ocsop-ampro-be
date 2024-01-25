package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00IssueManagementService;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.*;

public class Pme00IssueManagementLogic implements Pme00IssueManagementService {

    private final IssueManagementStore store;

    public Pme00IssueManagementLogic(IssueManagementStore store) {
        this.store = store;
    }

    @Override
    public List<IssueManagement> retrieve(int seq, String site) {
        return this.store.retrieve(seq,site);
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
    public IssueManagementResponse modify(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.update(issueManagement);
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
    public IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement newIssueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.create(newIssueManagement);
        //pls check to upload file at here
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
    public IssueManagementResponse remove(IssueManagementId seq) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.delete(seq);
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
    public Map<String, Object> findIssueInfo(String contents, String site, String module, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String contents_kr, String developer, String fromRegistrationStartDate, String toRegistrationEndDate, String fromRequestStartDate, String toRequestEndDate, int pageNo, int pageSize) {
        Pageable pageable;
        if(pageSize == 0){
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("status"));
        }

        IssueManagementResponse response = new IssueManagementResponse();
        List<Object[]> list = this.store.findIssueInfo(contents, site, module, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, contents_kr, developer, fromRegistrationStartDate, toRegistrationEndDate, fromRequestStartDate, toRequestEndDate, pageable);
        List<IssueManagement> issueManagementDtoList = new ArrayList<>();
        for(Object[] objects : list){
            issueManagementDtoList.add(new IssueManagement(objects));
        }
        Map<String, Object> rs = new HashMap<>();
        int total = store.findIssueReport(contents, site, module, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, contents_kr, developer, fromRegistrationStartDate, toRegistrationEndDate, fromRequestStartDate, toRequestEndDate);
        rs.put("total", total);
        rs.put("issueManagement", issueManagementDtoList);

        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return rs;
    }
    /**
     * Search issue management
     *
     * @param //site,module,division_flag,applied_period_flag,accept_flag,request_confirm,requester,contents,contents_kr,developer
     * @param //dto
     * @return List IssueManagement
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer) {
        IssueManagementResponse response = new IssueManagementResponse();
        List<IssueManagement> list = this.store.searchIssue(site,module,division_flag,applied_period_flag,accept_flag,request_confirm,requester,contents,contents_kr,developer);
        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return list;
    }

    /**
     * findIssueDto
     *
     * @param //contents, site, module, division_flag, applied_period_flag,
     *                 accept_flag, requester_confirm, requester, contents_kr, developer, registration_date, request_date
     * @param //dto
     * @return List IssueManagementDto
     * @author 202307_PhatNC
     * @since: 2024-01-24
     */
    @Override
    public List<IssueManagementDto> findIssueDto(String contents, String site, String module, String division_flag, String applied_period_flag, String accept_flag, String requester_confirm, String requester, String contents_kr, String developer, Date registration_date, String request_date) {
        List<Object[]> resultList = this.store.findIssueManagementDto(contents, site, module, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, contents_kr, developer, registration_date, request_date);
        List<IssueManagementDto> resultItemDtoList = new ArrayList<>();
        IssueManagementDto resultItemDto;
        for (Object[] objects : resultList) {
            resultItemDto = new IssueManagementDto(objects);
            resultItemDtoList.add(resultItemDto);
        }
        return resultItemDtoList;
    }
}
