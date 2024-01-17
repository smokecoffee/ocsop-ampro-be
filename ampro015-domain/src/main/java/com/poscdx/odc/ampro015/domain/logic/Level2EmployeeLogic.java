package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2EmployeeService;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


public class Level2EmployeeLogic implements Level2EmployeeService {
    @Override
    public Pme00AllLevel2EmployeeResponse getListPmeEmployee(ServiceLifecycle serviceLifecycle) {
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        List<Object[]> pme00Employees = serviceLifecycle.requestM00EmployeeService().findAllEmployee();
        List<Pme00Employee> pme00Employees1 = new ArrayList<>();
        for(Object[] object : pme00Employees){
            Pme00Employee employee = new Pme00Employee(object);
            pme00Employees1.add(employee);
        }
        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
        pme00AllLevel2EmployeeResponse.setListData(pme00Employees1);
        pme00AllLevel2EmployeeResponse.setMessage("Get all employee successfully");
        return pme00AllLevel2EmployeeResponse;
    }


    @Override
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(ServiceLifecycle serviceLifecycle,
                                                            String site, String status, String name, String empId){
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        List<Object[]> listPme00Employee = serviceLifecycle.requestM00EmployeeService()
                .searchPmeEmployee(site, status, name, empId);
        List<Pme00Employee> pme00Employees1 = new ArrayList<>();
        for(Object[] object : listPme00Employee){
            Pme00Employee employee = new Pme00Employee(object);
            pme00Employees1.add(employee);
        }
        pme00AllLevel2EmployeeResponse.setStatus(HttpStatus.OK.value());
        pme00AllLevel2EmployeeResponse.setListData(pme00Employees1);
        pme00AllLevel2EmployeeResponse.setMessage("Get all employee successfully");
        return pme00AllLevel2EmployeeResponse;
    }

    @Override
    public PmeSiteResponse findSiteEmp(ServiceLifecycle serviceLifecycle){
        PmeSiteResponse pmeSiteResponse = new PmeSiteResponse();
        final int cdTpId1 = 51;
        List<M00Codes030> m00Codes030s = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(cdTpId1);
        List<Pme00Site> pme00Sites = new ArrayList<>();
        for(M00Codes030 m00Codes030 : m00Codes030s){
            Pme00Site pme00Site = new Pme00Site();
            pme00Site.setCD_V(m00Codes030.getCdV());
            pme00Site.setCD_V_MEANING(m00Codes030.getCdvMeaning());
            pme00Sites.add(pme00Site);
        }
        pmeSiteResponse.setStatus(HttpStatus.OK.value());
        pmeSiteResponse.setListData(pme00Sites);
        pmeSiteResponse.setMessage("Get all site successfully");
        return pmeSiteResponse;
    }

    public PmeStatusResponse findStatus(ServiceLifecycle serviceLifecycle){
        PmeStatusResponse pmeStatusResponse = new PmeStatusResponse();
        final int cdTpId2 = 99;
        List<M00Codes030> m00Codes030s = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(cdTpId2);
        List<Pme00Status> pme00Statuses = new ArrayList<>();
        for(M00Codes030 m00Codes030 : m00Codes030s){
            Pme00Status pme00Status = new Pme00Status();
            pme00Status.setCD_V(m00Codes030.getCdV());
            pme00Status.setCD_V_MEANING(m00Codes030.getCdvMeaning());
            pme00Statuses.add(pme00Status);
        }
        pmeStatusResponse.setStatus(HttpStatus.OK.value());
        pmeStatusResponse.setListData(pme00Statuses);
        pmeStatusResponse.setMessage("Get all status successfully");
        return pmeStatusResponse;
    }
}
