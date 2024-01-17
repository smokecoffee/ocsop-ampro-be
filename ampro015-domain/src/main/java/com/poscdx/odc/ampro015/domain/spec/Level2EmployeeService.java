package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllLevel2EmployeeResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

public interface Level2EmployeeService {
    Pme00AllLevel2EmployeeResponse getListPmeEmployee(ServiceLifecycle serviceLifecycle);
    Pme00AllLevel2EmployeeResponse searchPmeEmployee(ServiceLifecycle serviceLifecycle,
                                                     String site, String status, String name, String empId);
}
