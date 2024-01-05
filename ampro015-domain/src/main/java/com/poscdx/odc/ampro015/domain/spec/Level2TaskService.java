package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Interface Level2TaskService
 *
 * @author 202296_Duong
 * @since 2023-11-11
 */
public interface Level2TaskService {

    /**
     * find task by taskId function
     *
     * @param serviceLifecycle
     * @param id
     * @return M00TaskDto
     */
    M00TaskDto findTaskByProjectNumberAndTaskName(ServiceLifecycle serviceLifecycle, M00TaskId id);

    /**
     * find task by projectNumber function
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @return List<M00TaskDto>
     */
    List<M00TaskDto> findAll(ServiceLifecycle serviceLifecycle, String projectNumber);

    /**
     * modify task function
     *
     * @param serviceLifecycle
     * @param updateTaskRequest
     * @return M00TaskDto
     */
    ResponseEntity<?> modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest);

    /**
     * create new task function
     *
     * @param serviceLifecycle
     * @param newTask
     * @return M00TaskDto
     */
    ResponseEntity<?> register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask);

    /**
     * remove task function
     *
     * @param serviceLifecycle
     * @param m00TaskId
     * @param isCheck
     * @return boolean
     */
    ResponseEntity<?> remove(ServiceLifecycle serviceLifecycle, Map<String, Object> m00TaskId, boolean isCheck);

    /**
     * search task with conditions function
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @param taskName
     * @param planDate
     * @param actualEndDate
     * @param status
     * @param empId
     * @param category
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDirection
     * @return List<M00TaskDto>
     */
    ResponseEntity<?> findTaskByConditions(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName,
                                           String planDate, String actualEndDate, String status, String empId, String category,
                                           int pageNo, int pageSize, String sortBy, String sortDirection);

    /**
     * search task by employeeId(tb_m00_employee) function
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @param taskName
     * @param status
     * @param employeeId
     * @return List<M00TaskDto>
     */
    List<M00TaskDto> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName, String status, String employeeId);
}
