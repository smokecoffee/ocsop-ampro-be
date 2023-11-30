package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;

import java.util.List;

public interface Pme00EmployeeTaskService {

    Pme00EmployeeTask find(M00TaskId m00TaskId);

    List<Pme00EmployeeTask> findAll();

    void modify(List<Pme00EmployeeTask> entityList);

    Pme00EmployeeTask register(Pme00EmployeeTask entity);

    void remove(M00TaskId m00TaskId);

}
