package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;

public interface Level2EmployeeService {
    Pme00AllLevel2EmployeeResponse searchPmeEmployee(ServiceLifecycle serviceLifecycle,
                                                     String site, String status, String name, String empId, String joinDateFrom, String joinDateTo);
    PmeSiteResponse findSiteEmp(ServiceLifecycle serviceLifecycle);
    PmeStatusResponse findStatus(ServiceLifecycle serviceLifecycle);
    Pme00AllLevel2EmployeeResponse addEmployee(ServiceLifecycle serviceLifecycle, Pme00Employee newEmployee);

    PmeRoleResponse findAllRole(ServiceLifecycle serviceLifecycle);
    Pme00AllLevel2EmployeeResponse editEmployee(ServiceLifecycle serviceLifecycle,
                                                List<Pme00Employee> pme00EmployeeList);

    Pme00GenderResponse findGender(ServiceLifecycle serviceLifecycle);

}
