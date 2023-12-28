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

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;

    @CrossOrigin
    @GetMapping(path = "/getAll")
    public List<M00TaskDto> findAll(@RequestParam String projectNumber) {
        return this.serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle, projectNumber);
    }

    @CrossOrigin
    @GetMapping(path = "/search")
    public List<M00TaskDto> searchTask(@RequestParam(required = false, name = "projectNumber") String projectNumber,
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
        if (StringUtils.isNotEmpty(employeeId)) {
            return this.serviceLifecycle.requestLevel2TaskService().findTaskByEmployeeId(serviceLifecycle, projectNumber, taskName, status, employeeId);
        }
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByConditions(serviceLifecycle, projectNumber,
                taskName, planDate, actualEndDate, status, employeeId, category, pageNo, pageSize, sortBy, sortDirection);
    }

    @CrossOrigin
    @GetMapping(path = "/getById")
    public M00TaskDto find(@RequestParam(value = "projectNumber") String projectNumber, @RequestParam(value = "taskName") String taskName) {
        M00TaskId requestId = new M00TaskId(projectNumber, taskName);
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByProjectNumberAndTaskName(serviceLifecycle, requestId);
    }

    @CrossOrigin
    @GetMapping(path = "/getByEmployeeId")
    public List<M00TaskDto> findByEmployeeId(@RequestParam(required = false, name = "projectNumber") String projectNumber,
                                             @RequestParam(required = false, name = "taskName") String taskName,
                                             @RequestParam(required = false, name = "status") String status,
                                             @RequestParam(value = "employeeId") String employeeId) {
        List<M00TaskDto> response = this.serviceLifecycle.requestLevel2TaskService().findTaskByEmployeeId(serviceLifecycle, projectNumber, taskName, status, employeeId);
        return response;
    }

    @CrossOrigin
    @PostMapping("")
    public M00TaskDto insertTask(@RequestBody M00TaskDto newTaskRequest) {
        Optional<M00TaskDto> responseData = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().register(serviceLifecycle, newTaskRequest));
        if (responseData.isPresent()) {
            return responseData.get();
        }
        return new M00TaskDto();
    }

    @CrossOrigin
    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody M00TaskDto newTaskRequest) throws JsonProcessingException {
        Optional<M00TaskDto> updatedTask = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, newTaskRequest));
        Map<String, Object> response = new HashMap<>();
        if (updatedTask.isPresent()) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "update success");
            response.put("data", updatedTask.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "Password incorrect!!");
        response.put("code", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @DeleteMapping("")
    public void deleteTask(@RequestBody Map<String, Object> m00TaskId) {
        this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId);
    }
}
