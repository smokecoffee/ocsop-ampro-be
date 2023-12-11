package com.poscodx.odc.ampro015.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;

    private final ObjectMapper objectMapper;

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
        return this.serviceLifecycle.requestTaskService().findAll(projectNumber, taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
    }

    @CrossOrigin
    @PostMapping(path = "/getById")
    public M00Task find(@RequestParam(value = "projectNumber") String projectNumber, @RequestParam(value = "taskName") String taskName) {
        M00TaskId requestId = new M00TaskId(projectNumber, taskName);
        return this.serviceLifecycle.requestTaskService().find(requestId);
    }

    @CrossOrigin
    @PostMapping("")
    public Map insertTask(@RequestBody Map<String, Object> newTaskRequest) {

        //convert json to DTO
        //insert task


        //inset to task member

        // return new list
        Map response = new HashMap<>();
        response.put(TASK_KEY, "");
        response.put(MEMBERS_KEY, "");

        return response;
    }

    @CrossOrigin
    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody Map<String, Object> newTaskRequest) throws JsonProcessingException {

        //convert json to DTO
        M00Task newTask = objectMapper.convertValue(newTaskRequest.get(TASK_KEY), M00Task.class);

        //check existed task
        M00TaskId updateTaskId = new M00TaskId(newTask.getProjectNumber(), newTask.getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(this.serviceLifecycle.requestTaskService().find(updateTaskId));

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
            Map<String, Object> data = new HashMap<>();
            data.put(TASK_KEY, newM00TaskRegisted);
            data.put(MEMBERS_KEY, pme00EmployeeTaskResponse);
            response.put(DATA_KEY, data);
            response.put(DESCRIPTION_KEY, "Insert Success!!!");
            response.put(STATUS_KEY, HttpStatus.CREATED.value());

            return ResponseEntity.ok(response);
        } else {
            Map response = new HashMap<>();
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, "Can't insert Success!!!");
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @CrossOrigin
    @DeleteMapping("")
    public ResponseEntity<?> deleteTask(@RequestBody M00TaskId m00TaskId) {
        Optional<M00Task> existedTask = Optional.ofNullable(this.serviceLifecycle.requestTaskService().find(m00TaskId));
        if (existedTask.isPresent()) {
            //delete data tb_pme00_employee_task
            List<Pme00EmployeeTask> pme00EmployeeTasksExisted = this.serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(m00TaskId);
            if (!pme00EmployeeTasksExisted.isEmpty()) {
                //M00EmployeeTaskId requestM00EmployeeTaskId = new M00EmployeeTaskId(existedTask.get().getProjectNumber(), existedTask.get().getTaskName(), existedTask.get().getEmpId());
                this.serviceLifecycle.requestPme00EmployeeTaskService().removeByListEmployeeTask(pme00EmployeeTasksExisted);
                this.serviceLifecycle.requestTaskService().remove(m00TaskId);
                Map response = new HashMap<>();
                response.put(DATA_KEY, "");
                response.put(DESCRIPTION_KEY, "Delete task success!!!");
                response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.ok(response);
            } else {
                Map response = new HashMap<>();
                response.put(DATA_KEY, "");
                response.put(DESCRIPTION_KEY, "Can't delete task success!!!");
                response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(response);
            }
        } else{
            Map response = new HashMap<>();
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, "Can't delete task success!!!");
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
