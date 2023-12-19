package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;

public interface Level2TaskService {

    M00TaskDto findTaskByProjectNumberAndTaskName(ServiceLifecycle serviceLifecycle, M00TaskId id);

    List<M00TaskDto> findAll(ServiceLifecycle serviceLifecycle, String projectNumber);

    M00TaskDto modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest);

    M00TaskDto register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask);

    void remove(ServiceLifecycle serviceLifecycle, M00TaskId id);

    List<M00TaskDto> findTaskByConditions(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName,
                                          String planDate, String actualEndDate, int pageNo, int pageSize, String sortBy,
                                          String sortDirection);
}
