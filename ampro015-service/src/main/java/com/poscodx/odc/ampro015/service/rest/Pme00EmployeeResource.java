package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.config.ConfigDef;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class Pme00EmployeeResource {

    private final ServiceLifecycle serviceLifecycle;

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('GET_EMPLOYEE,GET_EMPLOYEE_OWNER')")
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(
            @RequestParam(required = false, name = "site") String site,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "empId") String empId,
            @RequestParam(required = false, name = "joinDateFrom") String joinDateFrom,
            @RequestParam(required = false, name = "joinDateTo") String joinDateTo,
            @RequestParam(required = false, name = "gender") String gender){
        return this.serviceLifecycle.requestLevel2EmployeeService()
                .searchPmeEmployee(serviceLifecycle, site, status, name, empId, joinDateFrom, joinDateTo, gender);
    }

    @GetMapping("/findSiteEmp")
    @PreAuthorize("hasAnyAuthority('GET_EMPLOYEE')")
    public PmeSiteResponse findSiteEmp() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findSiteEmp(serviceLifecycle);
    }

    @GetMapping("/findStatusEmp")
    public PmeStatusResponse findStatus() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findStatus(serviceLifecycle);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADD_EMPLOYEE')")
    public Pme00AllLevel2EmployeeResponse addEmployee(@RequestParam ("data") String dtoString,
                                                     @RequestParam (value = "file", required = false) MultipartFile imageUpload) {
        return this.serviceLifecycle
                   .requestLevel2EmployeeService()
                   .addEmployee(serviceLifecycle, Pme00Employee.fromJson(dtoString), imageUpload);
    }

    @GetMapping("/findAllRole")
    public PmeRoleResponse findAllRole() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findAllRole(serviceLifecycle);
    }
//
//    @PutMapping("/")
//<<<<<<< HEAD
//    public Pme00AllLevel2EmployeeResponse editEmployee(@Valid @RequestBody Pme00Employee  pme00Employee) {
////    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
//        return this.serviceLifecycle.requestLevel2EmployeeService().editEmployee(serviceLifecycle,pme00Employee);

    @PreAuthorize("hasAnyAuthority('UPDATE_EMPLOYEE')")
    public Pme00AllLevel2EmployeeResponse editEmployee(@RequestParam ("data") String dtoString,
     @RequestParam (value = "file", required = false) MultipartFile imageUpload) {
        return this.serviceLifecycle.requestLevel2EmployeeService()
                .editEmployee(serviceLifecycle,Pme00Employee.fromJson(dtoString),imageUpload);
    }

    @GetMapping("/findGender")
    public Pme00GenderResponse findGender() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findGender(serviceLifecycle);
    }

}
