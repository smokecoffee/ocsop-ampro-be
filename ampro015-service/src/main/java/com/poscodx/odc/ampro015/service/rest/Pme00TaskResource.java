package com.poscodx.odc.ampro015.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;

    private final ObjectMapper objectMapper;

    private static final String TASK_KEY = "task";
    private static final String MEMBERS_KEY = "members";

    @CrossOrigin
    @GetMapping(path = "getAll")
    public List<M00Task> findAll(@RequestParam String projectNumber, @RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "20", required = false) int pageSize, @RequestParam(defaultValue = "TASK_NAME", required = false) String sortBy, @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        return this.serviceLifecycle.requestTaskService().findAll(projectNumber, pageNo, pageSize, sortBy, sortDirection);
    }

    @CrossOrigin
    @PostMapping(path = "getById")
    public M00Task find(@RequestParam(value = "projectNumber") String projectNumber, @RequestParam(value = "taskName") String taskName) {
        M00TaskId requestId = new M00TaskId(projectNumber, taskName);
        return this.serviceLifecycle.requestTaskService().find(requestId);
    }

    @CrossOrigin
    @PostMapping("")
    public Map insertTask(@RequestBody Map<String, Object> newTaskRequest) {

        //convert json to DTO
        M00Task newTask = objectMapper.convertValue(newTaskRequest.get(TASK_KEY), M00Task.class);
        List<Pme00EmployeeTask> pme00EmployeeTask = new ArrayList<>();

        List<Object> pme00EmployeeTasksList = objectMapper.convertValue(newTaskRequest.get(MEMBERS_KEY), List.class);

        pme00EmployeeTasksList.forEach(pme00EmployeeTask1 -> {
            pme00EmployeeTask.add(objectMapper.convertValue(pme00EmployeeTask1, Pme00EmployeeTask.class));
        });
        //insert task
        M00Task newM00TaskRegisted = this.serviceLifecycle.requestTaskService().register(newTask);

        //inset to task member
        List<Pme00EmployeeTask> pme00EmployeeTaskResponse = this.serviceLifecycle.requestPme00EmployeeTaskService().createFromList(pme00EmployeeTask);

        // return new list
        Map response = new HashMap<>();
        response.put(TASK_KEY, newM00TaskRegisted);
        response.put(MEMBERS_KEY, pme00EmployeeTaskResponse);

        return response;
    }

    @CrossOrigin
    @PutMapping("")
    public Map updateTask(@RequestBody Map<String, Object> newTaskRequest) throws JsonProcessingException {

        //convert json to DTO
        M00Task newTask = objectMapper.convertValue(newTaskRequest.get(TASK_KEY), M00Task.class);

        //check existed task
        M00TaskId updateTaskId = new M00TaskId(newTask.getProjectNumber(), newTask.getTaskName());
        Optional<M00Task> existedTask = Optional.of(this.serviceLifecycle.requestTaskService().find(updateTaskId));

        if (existedTask.isPresent()) {
            List<Pme00EmployeeTask> pme00EmployeeTask = new ArrayList<>();

            List<Object> pme00EmployeeTasksList = objectMapper.convertValue(newTaskRequest.get(MEMBERS_KEY), List.class);

            pme00EmployeeTasksList.forEach(pme00EmployeeTask1 -> {
                pme00EmployeeTask.add(objectMapper.convertValue(pme00EmployeeTask1, Pme00EmployeeTask.class));
            });
            //insert task
            M00Task newM00TaskRegisted = this.serviceLifecycle.requestTaskService().register(newTask);

            //inset to task member
            List<Pme00EmployeeTask> pme00EmployeeTaskResponse = this.serviceLifecycle.requestPme00EmployeeTaskService().createFromList(pme00EmployeeTask);

            // return new list
            Map response = new HashMap<>();
            response.put(TASK_KEY, newM00TaskRegisted);
            response.put(MEMBERS_KEY, pme00EmployeeTaskResponse);

            return response;
        }
        return null;
    }

    @CrossOrigin
    @DeleteMapping
    public void deleteTask(@RequestBody M00TaskId m00TaskId) {
        this.serviceLifecycle.requestTaskService().remove(m00TaskId);
    }

}
