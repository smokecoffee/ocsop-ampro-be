package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.TaskSearchDTO;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Router API for task management
 *
 * @author 202296_Duong
 * @since 2023-11-11
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;

    /**
     * Get List Task function
     *
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @GetMapping(path = "/getAll")
    @PreAuthorize("hasAnyAuthority('GET_TASK,GET_TASK_OWNER')")
    public List<M00TaskDto> findAll(@RequestParam String projectNumber) {
        return this.serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle, projectNumber);
    }

    /**
     * Get List Task function
     *
     * @param projectNumber, taskName, planDate, actualEndDate, status, employeeId, category
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @GetMapping(path = "/search")
    @PreAuthorize("hasAnyAuthority('GET_TASK,GET_TASK_OWNER')")
    public ResponseEntity<?> searchTask(@RequestParam(required = false, name = "projectNumber") String projectNumber,
                                       @RequestParam(required = false, name = "taskName") String taskName,
                                       @RequestParam(required = false, name = "planDate") String planDate,
                                       @RequestParam(required = false, name = "actualEndDate") String actualEndDate,
                                       @RequestParam(required = false, name = "status") String status,
                                       @RequestParam(required = false, name = "employeeId") String employeeId,
                                       @RequestParam(required = false, name = "category") String category,
                                       @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                       @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize,
                                       @RequestParam(required = false, defaultValue = "lastUpdateTimestamp", name = "sortBy") String sortBy,
                                       @RequestParam(required = false, defaultValue = "ASC", name = "sortDirection") String sortDirection) {
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByConditions(serviceLifecycle, projectNumber,
                taskName, planDate, actualEndDate, status, employeeId, category, pageNo, pageSize, sortBy, sortDirection);
    }

    /**
     * Get List Task function
     *
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADD_TASK')")
    public boolean insertTask(@RequestParam ("data") String dtoString,
                              @RequestParam (value = "file", required = false) MultipartFile fileUpload) throws SQLException {
        return false;//this.serviceLifecycle.requestLevel2TaskService().register(serviceLifecycle, M00TaskDto.fromJson(dtoString), fileUpload);
    }

    /**
     * Update Task function
     *
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('UPDATE_TASK,UPDATE_TASK_OWNER')")
    public boolean updateTask(@RequestParam ("data") String dtoString,
                              @RequestParam (value = "file", required = false) MultipartFile fileUpload) throws SQLException {
        //return this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, M00TaskDto.fromJson(dtoString), fileUpload);
        return false;
    }

    /**
     * Delete Task function
     *
     * @return ResponseEntity
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('DELETE_TASK,DELETE_TASK_OWNER')")
    public ResponseEntity<?> deleteTask(@RequestBody Map<String, Object> m00TaskId) {
        return this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId, true);
    }

    /**
     * Get List Task function
     *
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('GET_TASK,GET_TASK_OWNER')")
    public ResponseEntity<?> searchTask(@RequestBody TaskSearchDTO searchTask) {
        String employeeId = searchTask.getEmpId();
        if (StringUtils.isNotEmpty(employeeId)) {
            return this.serviceLifecycle.requestLevel2TaskService().findTaskByEmployeeId(serviceLifecycle, searchTask);
        }
        return this.serviceLifecycle.requestLevel2TaskService().searchTask(serviceLifecycle, searchTask);
    }


    /**
     * Get Creator function
     *
     * @return EmployeeDto
     * @author 202260_tri
     * @since 2024-01-11
     */
    @GetMapping("/employee/{id}")
    public M00Employee getTaskOwner(@PathVariable("id") String employeeId) {
        return this.serviceLifecycle.requestLevel2TaskService().getCreator(serviceLifecycle, employeeId);
    }


}
