package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00IssueManagementService;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public IssueManagementResponse modify(ServiceLifecycle serviceLifecycle,IssueManagement issueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.update(issueManagement);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been updated");
        return response;
    }


    @Override
    public IssueManagementResponse create(ServiceLifecycle serviceLifecycle,IssueManagement newIssueManagement) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.create(newIssueManagement);
        //pls check to upload file at here

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("This issue has been created");
        return response;
    }

    @Override
    public IssueManagementResponse remove(IssueManagementId seq) {
        IssueManagementResponse response = new IssueManagementResponse();
        store.delete(seq);
       // pls remove file, confirm with Mr Tri
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("This issue has been deleted");
        return response;
    }

    @Override
    public List<IssueManagementDto> findIssueInfo(String contents, String site, String module, String division_flag,
                                               String applied_period_flag, String accept_flag, String requester_confirm,
                                               String requester, String contents_kr, String developer,
                                               String fromRegistrationStartDate,String toRegistrationEndDate,String fromRequestStartDate,String toRequestEndDate) {
        IssueManagementResponse response = new IssueManagementResponse();
        if(contents == null){
            contents = "";
        }
        if(site == null){
            site = "";
        }
        if(module == null){
            module = "";
        }
        if(division_flag == null){
            division_flag = "";
        }
        if(applied_period_flag == null){
            applied_period_flag = "";
        }
        if(accept_flag == null){
            accept_flag = "";
        }
        if(requester_confirm == null){
            requester_confirm = "";
        }
        if(requester == null){
            requester = "";
        }
        if(contents_kr == null){
            contents_kr = "";
        }
        if(developer == null){
            developer = "";
        }
        List<Object[]> list = this.store.findIssueInfo(contents, site, module, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, contents_kr, developer, fromRegistrationStartDate, toRegistrationEndDate,fromRequestStartDate, toRequestEndDate);
        List<IssueManagementDto> issueManagementDtoList = new ArrayList<>();
        for(Object[] objects : list){
            issueManagementDtoList.add(new IssueManagementDto(objects));
        }
        for (IssueManagementDto dto : issueManagementDtoList) {

        }
        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return issueManagementDtoList;
    }

    @Override
    public List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag, String request_confirm, String requester, String contents, String contents_kr, String developer) {
        IssueManagementResponse response = new IssueManagementResponse();
        List<IssueManagement> list = this.store.searchIssue(site,module,division_flag,applied_period_flag,accept_flag,request_confirm,requester,contents,contents_kr,developer);
        response.setStatus(HttpStatus.FOUND.value());
        response.setMessage("OK");
        return list;
    }

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
