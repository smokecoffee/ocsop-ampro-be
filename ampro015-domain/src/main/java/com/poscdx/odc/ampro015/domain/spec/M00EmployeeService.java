package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;

import java.util.List;
import java.util.Set;

public interface M00EmployeeService {

    M00Employee find(String id);
    List<M00Employee> findAll();

    M00Employee modify(M00Employee requestUpdateTask);
    void modifyByList(List<M00Employee> entityList);
    Pme00Employee modifyByList2(Pme00Employee pme00Employee);

    M00Employee register(M00Employee entity);

    void remove(String id);

    List<Object[]> getEmployeeByEmployeeId(Set<String> empId);

    List<M00Employee> getActiveEmployee();

    List<Object[]> findAllEmployee();

    List<Object[]> searchPmeEmployee(String site, String status, String name, String empId, String joinDateFrom, String joinDateTo, String gender);
}
