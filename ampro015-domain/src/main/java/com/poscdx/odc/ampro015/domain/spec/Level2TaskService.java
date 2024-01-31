package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.TaskSearchDTO;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
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
    boolean modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest, MultipartFile imageUpload, MultipartFile fileUpload) throws SQLException;

    /**
     * create new task function
     *
     * @param serviceLifecycle
     * @param newTask
     * @return M00TaskDto
     */
    ResponseEntity<?> register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask);
//    boolean register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask, MultipartFile imageUpload, MultipartFile fileUpload) throws SQLException;

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
     * @param searchTask
     * @return List<M00TaskDto>
     */
    ResponseEntity<?> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, TaskSearchDTO searchTask);


    /**
     * search task with conditions function
     *
     * @param serviceLifecycle
     * @return List<M00TaskDto>
     */
    ResponseEntity<?> searchTask(ServiceLifecycle serviceLifecycle, TaskSearchDTO searchTask);

    M00Employee getCreator(ServiceLifecycle serviceLifecycle, String employeeId);
}
