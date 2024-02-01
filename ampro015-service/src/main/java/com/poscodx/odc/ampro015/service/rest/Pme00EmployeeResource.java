package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import com.poscodx.odc.ampro015.service.PermissionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        if (!PermissionValidation.validateSearchEmployee(empId)) {
            return new Pme00AllLevel2EmployeeResponse(HttpStatus.FORBIDDEN.value(), null, Utils.NO_PERMISSION);
        }
        return this.serviceLifecycle.requestLevel2EmployeeService()
                .searchPmeEmployee(serviceLifecycle, site, status, name, empId, joinDateFrom, joinDateTo, gender);
    }

    @GetMapping("/findSiteEmp")
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
//<<<<<<< HEAD
//    public Pme00AllLevel2EmployeeResponse editEmployee(@Valid @RequestBody Pme00Employee  pme00Employee) {
////    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
//        return this.serviceLifecycle.requestLevel2EmployeeService().editEmployee(serviceLifecycle,pme00Employee);

    @PutMapping("/")
    @PreAuthorize("hasAnyAuthority('UPDATE_EMPLOYEE,UPDATE_EMPLOYEE_OWNER')")
    public Pme00AllLevel2EmployeeResponse editEmployee(@RequestBody Pme00Employee pme00Employee) {
        if (!PermissionValidation.validateUpdateEmployee(pme00Employee)) {
            return new Pme00AllLevel2EmployeeResponse(HttpStatus.FORBIDDEN.value(), null, Utils.NO_PERMISSION);
        }
        return this.serviceLifecycle.requestLevel2EmployeeService().editEmployee(serviceLifecycle,pme00Employee);
    }

    @GetMapping("/findGender")
    public Pme00GenderResponse findGender() {
        return this.serviceLifecycle.requestLevel2EmployeeService().findGender(serviceLifecycle);
    }

}
