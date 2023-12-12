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
    public ResponseEntity<?> findAll(@RequestParam String projectNumber,
                                     @RequestParam(required = false, defaultValue = "") String taskName,
                                     @RequestParam(required = false, defaultValue = "") String planDate,
                                     @RequestParam(required = false, defaultValue = "") String actualEndDate,
                                     @RequestParam(defaultValue = "0", required = false) int pageNo,
                                     @RequestParam(defaultValue = "20", required = false) int pageSize,
                                     @RequestParam(defaultValue = "TASK_NAME", required = false) String sortBy,
                                     @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {

        Map response = new HashMap<>();
        List<M00TaskDto> responseData = this.serviceLifecycle.requestLevel2TaskService().findAll(serviceLifecycle, projectNumber, taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
        if (responseData.isEmpty()) {
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, String.format("Not found any task with projectNumber %s", projectNumber));
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        } else {
            response.put(DATA_KEY, responseData);
            response.put(DESCRIPTION_KEY, "List task");
            response.put(STATUS_KEY, HttpStatus.OK.value());
            return ResponseEntity.ok(response);

        }
    }

    @CrossOrigin
    @GetMapping(path = "/getById")
    public ResponseEntity<?> find(@RequestParam(value = "projectNumber") String projectNumber, @RequestParam(value = "taskName") String taskName) {
        M00TaskId requestId = new M00TaskId(projectNumber, taskName);

        Map response = new HashMap<>();
        Optional<M00TaskDto> responseData = Optional.ofNullable(this.serviceLifecycle.requestLevel2TaskService().findTaskByProjectNumberAndTaskName(serviceLifecycle, requestId));
        if (responseData.isPresent()) {
            response.put(DATA_KEY, responseData);
            response.put(DESCRIPTION_KEY, "List task by taskId");
            response.put(STATUS_KEY, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            response.put(DATA_KEY, "");
            response.put(DESCRIPTION_KEY, String.format("Not found any task with taskId %s", requestId.toString()));
            response.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);

        }

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
    public ResponseEntity<?> deleteTask(@RequestBody M00TaskId m00TaskId) {
        Optional<M00Task> existedTask = Optional.ofNullable(this.serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(m00TaskId));
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
