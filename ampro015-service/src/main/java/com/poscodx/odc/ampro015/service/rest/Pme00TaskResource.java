package com.poscodx.odc.ampro015.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Router API for task management
 *
 * @author 202296_Duong
 * @since 2023-11-11
 */
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
    @CrossOrigin
    @GetMapping(path = "/getAll")
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
    @CrossOrigin
    @GetMapping(path = "/search")
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
    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<?> insertTask(@RequestBody M00TaskDto newTaskRequest) {
        return this.serviceLifecycle.requestLevel2TaskService().register(serviceLifecycle, newTaskRequest);
    }

    /**
     * Update Task function
     *
     * @return M00TaskDto
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @CrossOrigin
    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody M00TaskDto newTaskRequest) {
        return this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, newTaskRequest);
    }

    /**
     * Delete Task function
     *
     * @return ResponseEntity
     * @author 202296_Duong
     * @since 2023-11-11
     */
    @CrossOrigin
    @DeleteMapping("")
    public ResponseEntity<?> deleteTask(@RequestBody Map<String, Object> m00TaskId) {
        return this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId, true);
    }
}
