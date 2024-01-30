package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
import com.poscdx.odc.ampro015.domain.spec.M00EmployeeService;
import com.poscdx.odc.ampro015.domain.store.M00EmployeeStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class M00EmployeeLogic implements M00EmployeeService {
    private final M00EmployeeStore store;

    public M00EmployeeLogic(M00EmployeeStore store) {
        this.store = store;
    }

    @Override
    public M00Employee find(String id) {
        return store.retrieve(id);
    }

    @Override
    public List<M00Employee> findAll() {
        return store.retrieveAll();
    }

    @Override
    public M00Employee modify(M00Employee requestUpdateTask) {
        return this.store.update(requestUpdateTask);
    }

    @Override
    public void modifyByList(List<M00Employee> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00Employee modifyByList2(Pme00Employee pme00Employee) {
        return this.store.update2(pme00Employee);
    }

    @Override
    public M00Employee register(M00Employee entity) {
        return store.create(entity);
    }

    @Override
    public void remove(String id) {
        store.delete(id);
    }

    @Override
    public List<Object[]> getEmployeeByEmployeeId(Set<String> empId) {
        return store.getEmployeeByEmployeeId(empId);
    }

    @Override
    public List<M00Employee> getActiveEmployee() {
        List<Object[]> resultList = this.store.getActiveEmployee();
        List<M00Employee> m00EmployeeList = new ArrayList<>();
        for (Object[] obj : resultList) {
            m00EmployeeList.add(new M00Employee(obj));
        }
        return m00EmployeeList;
    }
    @Override
    public List<Object[]> findAllEmployee(){
        return store.findAllEmployee();
    }

    @Override
    public List<Object[]> searchPmeEmployee(String site, String status, String name, String empId, String joinDateFrom, String joinDateTo, String gender){
        return store.searchPmeEmployee(site, status, name, empId, joinDateFrom, joinDateTo, gender);
    }

}
