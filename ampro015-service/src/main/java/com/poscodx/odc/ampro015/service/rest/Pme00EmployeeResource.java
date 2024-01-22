package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class Pme00EmployeeResource {

    private final ServiceLifecycle serviceLifecycle;

    @GetMapping("/search")
    @CrossOrigin
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(
            @RequestParam(required = false, name = "site") String site,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "empId") String empId,
            @RequestParam(required = false, name = "joinDateFrom") String joinDateFrom,
            @RequestParam(required = false, name = "joinDateTo") String joinDateTo){
        return this.serviceLifecycle.requestLevel2EmployeeService()
                .searchPmeEmployee(serviceLifecycle, site, status, name, empId, joinDateFrom, joinDateTo);
    }

    @GetMapping("/findSiteEmp")
    @CrossOrigin
    public PmeSiteResponse findSiteEmp() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findSiteEmp(serviceLifecycle);
    }

    @GetMapping("/findStatusEmp")
    @CrossOrigin
    public PmeStatusResponse findStatus() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findStatus(serviceLifecycle);
    }

    @PostMapping("/")
    @CrossOrigin
    public Pme00AllLevel2EmployeeResponse addEmpoyee(@RequestBody Pme00Employee newEmployee) {
        return this.serviceLifecycle.requestLevel2EmployeeService().addEmployee(serviceLifecycle,newEmployee);
    }

    @GetMapping("/findAllRole")
    @CrossOrigin
    public PmeRoleResponse findAllRole() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findAllRole(serviceLifecycle);
    }

    @PutMapping("/")
    @CrossOrigin
    public Pme00AllLevel2EmployeeResponse editEmployee(@RequestBody List<Pme00Employee> pme00EmployeeList) {
        return this.serviceLifecycle.requestLevel2EmployeeService().editEmployee(serviceLifecycle,pme00EmployeeList);
    }
}
