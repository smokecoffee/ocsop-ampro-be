package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;

import java.util.List;

public interface Pme00EmployeeTaskStore {
    Pme00EmployeeTask retrieve(M00TaskId m00TaskId);

    List<Pme00EmployeeTask> retrieveAll();

    Pme00EmployeeTask update(Pme00EmployeeTask Pme00EmployeeTask);

    Pme00EmployeeTask create(Pme00EmployeeTask entity);

    void delete(M00TaskId m00TaskId);

}
