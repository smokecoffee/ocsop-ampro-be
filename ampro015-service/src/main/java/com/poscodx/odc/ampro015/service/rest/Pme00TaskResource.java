package com.poscodx.odc.ampro015.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
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
    private static final String TASK_KEY = "task";
    private static final String DATA_KEY = "data";
    private static final String STATUS_KEY = "status";
    private static final String DESCRIPTION_KEY = "description";
    private static final String MEMBERS_KEY = "members";

    @CrossOrigin
    @GetMapping(path = "/getAll")
    public List<M00TaskDto> findAll(@RequestParam String projectNumber,
                                    @RequestParam(required = false, defaultValue = "") String taskName,
                                    @RequestParam(required = false, defaultValue = "") String planDate,
                                    @RequestParam(required = false, defaultValue = "") String actualEndDate,
                                    @RequestParam(defaultValue = "0", required = false) int pageNo,
                                    @RequestParam(defaultValue = "20", required = false) int pageSize,
                                    @RequestParam(defaultValue = "TASK_NAME", required = false) String sortBy,
                                    @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        return this.serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle, projectNumber, taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
    }

    @CrossOrigin
    @GetMapping(path = "/getById")
    public M00TaskDto find(@RequestParam(value = "projectNumber") String projectNumber, @RequestParam(value = "taskName") String taskName) {
        M00TaskId requestId = new M00TaskId(projectNumber, taskName);
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByProjectNumberAndTaskName(serviceLifecycle, requestId);
    }

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<?> insertTask(@RequestBody M00TaskDto newTaskRequest) {

        Optional<M00TaskDto> responseData = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().register(serviceLifecycle, newTaskRequest));

        Map response = new HashMap<>();
        if (responseData.isPresent()) {
            response.put(DATA_KEY, responseData.get());
            response.put(DESCRIPTION_KEY, "Add new task success");
            response.put(STATUS_KEY, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, String.format("The task with taskId  %s is already existed", newTaskRequest.toString()));
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @CrossOrigin
    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody M00TaskDto newTaskRequest) throws JsonProcessingException {

        Optional<M00TaskDto> updatedTask = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, newTaskRequest));

        Map response = new HashMap<>();
        if (updatedTask.isPresent()) {
            response.put(DATA_KEY, updatedTask.get());
            response.put(DESCRIPTION_KEY, "Updated task success!!!");
            response.put(STATUS_KEY, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, "Can't update task!!!");
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @CrossOrigin
    @DeleteMapping("")
    public void deleteTask(@RequestBody M00TaskId m00TaskId) {
        this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId);
    }
}
