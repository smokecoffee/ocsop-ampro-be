package com.poscodx.odc.ampro015.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;

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
    public M00TaskDto insertTask(@RequestBody M00TaskDto newTaskRequest) {
        Optional<M00TaskDto> responseData = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().register(serviceLifecycle, newTaskRequest));
        return responseData.get();
    }

    @CrossOrigin
    @PutMapping("")
    public M00TaskDto updateTask(@RequestBody M00TaskDto newTaskRequest) throws JsonProcessingException {
        Optional<M00TaskDto> updatedTask = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, newTaskRequest));
        return updatedTask.get();
    }

    @CrossOrigin
    @DeleteMapping("")
    public void deleteTask(@RequestBody M00TaskId m00TaskId) {
        this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId);
    }
}
