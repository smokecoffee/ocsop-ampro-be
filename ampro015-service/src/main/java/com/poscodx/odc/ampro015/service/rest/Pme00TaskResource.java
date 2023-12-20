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
    public List<M00TaskDto> findAll(@RequestParam String projectNumber) {
        return this.serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle, projectNumber);
    }

    @CrossOrigin
    @GetMapping(path = "/search")
    public List<M00TaskDto> searchTask(@RequestParam(name = "projectNumber") String projectNumber,
                                       @RequestParam(required = false, defaultValue = "", name = "taskName") String taskName,
                                       @RequestParam(required = false, defaultValue = "", name = "planDate") String planDate,
                                       @RequestParam(required = false, defaultValue = "", name = "actualEndDate") String actualEndDate,
                                       @RequestParam(defaultValue = "0", required = false, name = "pageNo") int pageNo,
                                       @RequestParam(defaultValue = "20", required = false, name = "pageSize") int pageSize,
                                       @RequestParam(defaultValue = "t.LAST_UPDATE_TIMESTAMP", required = false, name = "sortBy") String sortBy,
                                       @RequestParam(defaultValue = "ASC", required = false, name = "sortDirection") String sortDirection) {
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByConditions(serviceLifecycle, projectNumber,
                taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
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
        if(responseData.isPresent()){
            return responseData.get();
        }
        return new M00TaskDto();
    }

    @CrossOrigin
    @PutMapping("")
    public M00TaskDto updateTask(@RequestBody M00TaskDto newTaskRequest) throws JsonProcessingException {
        Optional<M00TaskDto> updatedTask = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().modify(serviceLifecycle, newTaskRequest));
        if(updatedTask.isPresent()){
            return updatedTask.get();
        }
        return new M00TaskDto();
    }

    @CrossOrigin
    @DeleteMapping("")
    public void deleteTask(@RequestBody M00TaskId m00TaskId) {
        this.serviceLifecycle.requestLevel2TaskService().remove(serviceLifecycle, m00TaskId);
    }

    @CrossOrigin
    @GetMapping(path = "/v0/search")
    public List<M00TaskDto> searchTaskV0(@RequestParam(name = "projectNumber") String projectNumber,
                                       @RequestParam(required = false, defaultValue = "", name = "taskName") String taskName,
                                       @RequestParam(required = false, defaultValue = "", name = "planDate") String planDate,
                                       @RequestParam(required = false, defaultValue = "", name = "actualEndDate") String actualEndDate,
                                       @RequestParam(defaultValue = "", required = false, name = "status") String status,
                                       @RequestParam(defaultValue = "0", required = false, name = "pageNo") int pageNo,
                                       @RequestParam(defaultValue = "20", required = false, name = "pageSize") int pageSize,
                                       @RequestParam(defaultValue = "t.LAST_UPDATE_TIMESTAMP", required = false, name = "sortBy") String sortBy,
                                       @RequestParam(defaultValue = "ASC", required = false, name = "sortDirection") String sortDirection) {
        return this.serviceLifecycle.requestLevel2TaskService().findTaskByConditionsV0(serviceLifecycle, projectNumber,
                taskName, planDate, actualEndDate, status, pageNo, pageSize, sortBy, sortDirection);
    }
}
