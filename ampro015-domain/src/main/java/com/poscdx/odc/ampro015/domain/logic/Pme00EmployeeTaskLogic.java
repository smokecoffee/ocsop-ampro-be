package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeTaskService;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;

import java.util.List;
import java.util.Set;

public class Pme00EmployeeTaskLogic implements Pme00EmployeeTaskService {
    private final Pme00EmployeeTaskStore store;

    public Pme00EmployeeTaskLogic(Pme00EmployeeTaskStore store) {
        this.store = store;
    }

    @Override
    public Pme00EmployeeTask find(M00EmployeeTaskId m00EmployeeTaskId) {
        return this.store.retrieve(m00EmployeeTaskId);
    }

    @Override
    public List<Pme00EmployeeTask> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00EmployeeTask> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00EmployeeTask register(Pme00EmployeeTask entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(M00EmployeeTaskId m00EmployeeTaskId) {
        this.store.delete(m00EmployeeTaskId);
    }

    @Override
    public List<Pme00EmployeeTask> createFromList(List<Pme00EmployeeTask> employeeTaskList) {
        return this.store.createFromList(employeeTaskList);
    }

    @Override
    public List<Pme00EmployeeTask> findAllByTaskId(M00TaskId requestTaskId) {
        return this.store.retrieveAllByTaskId(requestTaskId);
    }

    @Override
    public List<Pme00EmployeeTask> findAllByProjectNumber(String projectNumber) {
        return this.store.retrieveAllByProjectNumber(projectNumber);
    }

    @Override
    public List<Pme00EmployeeTask> findAllBySetProjectNumber(Set<String> setProjectNumber) {
        return this.store.retrieveAllBySetProjectNumber(setProjectNumber);
    }
}