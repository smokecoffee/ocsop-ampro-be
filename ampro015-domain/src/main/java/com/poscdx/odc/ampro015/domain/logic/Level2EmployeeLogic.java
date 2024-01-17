package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllLevel2EmployeeResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
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
                                                            String site, String status, String name){
        Pme00AllLevel2EmployeeResponse pme00AllLevel2EmployeeResponse = new Pme00AllLevel2EmployeeResponse();
        List<Object[]> listPme00Employee = serviceLifecycle.requestM00EmployeeService().searchPmeEmployee(site, status, name);
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
}
