package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;
import java.util.Map;

add comment
public interface Level2TaskService {
    add comment
    M00TaskDto findTaskByProjectNumberAndTaskName(ServiceLifecycle serviceLifecycle, M00TaskId id);
    add comment
    List<M00TaskDto> findAll(ServiceLifecycle serviceLifecycle, String projectNumber);
    add comment
    M00TaskDto modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest);
    add comment
    M00TaskDto register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask);
    add comment
    boolean remove(ServiceLifecycle serviceLifecycle, Map<String, Object> m00TaskId);
    add comment
    List<M00TaskDto> findTaskByConditions(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName,
                                          String planDate, String actualEndDate, String status, String empId, String category,
                                          int pageNo, int pageSize, String sortBy, String sortDirection);
    add comment
    List<M00TaskDto> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName, String status, String employeeId);
}
