package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;

import java.util.List;
import java.util.Set;

public interface M00EmployeeStore {
    M00Employee retrieve(String id);

    M00Employee update(M00Employee entity);

    M00Employee create(M00Employee entity);

    void delete(String id);

    List<M00Employee> retrieveAll();

    List<Object[]> getEmployeeByEmployeeId(Set<String> empId);

    List<Object[]> getActiveEmployee();

}
