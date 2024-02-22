package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;

import java.util.List;
import java.util.Set;

public interface Pme00EmployeeTaskService {

    Pme00EmployeeTask find(M00EmployeeTaskId m00EmployeeTaskId);

    List<Pme00EmployeeTask> findAll();
    List<Pme00EmployeeTask> findAllByProjectNumber(String projectNumber);

    void modify(List<Pme00EmployeeTask> entityList);

    List<Pme00EmployeeTask> findAllByTaskId(M00TaskId requestTaskId);

    Pme00EmployeeTask register(Pme00EmployeeTask entity);

    void remove(M00EmployeeTaskId m00EmployeeTaskId);

    List<Pme00EmployeeTask> createFromList(List<Pme00EmployeeTask> entity);

    List<Pme00EmployeeTask> findAllBySetProjectNumber(Set<String> setProjectNumber);
}