package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllLevel2EmployeeResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

public interface Level2EmployeeService {
    Pme00AllLevel2EmployeeResponse getListPmeEmployee(ServiceLifecycle serviceLifecycle);

}
