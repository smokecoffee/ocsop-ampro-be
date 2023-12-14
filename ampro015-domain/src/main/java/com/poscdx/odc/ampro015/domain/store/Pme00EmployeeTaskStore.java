package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;

import java.util.List;

public interface Pme00EmployeeTaskStore {
    Pme00EmployeeTask retrieve(M00EmployeeTaskId m00EmployeeTaskId);

    List<Pme00EmployeeTask> retrieveAll();
    List<Pme00EmployeeTask> retrieveAllByProjectNumber(String projectNumber);

    Pme00EmployeeTask update(Pme00EmployeeTask Pme00EmployeeTask);

    Pme00EmployeeTask create(Pme00EmployeeTask entity);

    void delete(M00EmployeeTaskId m00EmployeeTaskId);

    List<Pme00EmployeeTask> createFromList(List<Pme00EmployeeTask> entity);

    List<Pme00EmployeeTask> retrieveAllByTaskId(M00TaskId reqM00TaskId);

    void deleteEmployeeTaskListByTaskId(String projectNumber, String taskName);

}
