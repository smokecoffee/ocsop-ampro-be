package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Level2TaskLogic implements Level2TaskService {
    @Override
    public M00TaskDto findTaskByProjectNumberAndTaskName(ServiceLifecycle serviceLifecycle, M00TaskId searchTaskId) {
        Optional<M00Task> m00Task = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(searchTaskId));
        if (m00Task.isPresent()) {
            List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(searchTaskId);
            M00TaskDto taskResponse = new M00TaskDto();
            taskResponse.setTask(m00Task.get());
            taskResponse.setMembers(pme00EmployeeTaskList);
            return taskResponse;
        }
        return null;
    }

    @Override
    public List<M00TaskDto> findAll(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName, String planDate, String actualEndDate, int pageNo, int pageSize, String sortBy, String sortDirection) {
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestTaskService().findAll(projectNumber, taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
        //findAllEmplTask
        M00TaskId requestTaskId = new M00TaskId(projectNumber, taskName);
        // TODO: update query find findAllByTaskId, when task searching send only projectNumber
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestTaskId);

        List<M00TaskDto> responseList = new ArrayList<>();
        //append member to task
        m00TaskDtoList.forEach(m00Task -> {
            M00TaskDto response = new M00TaskDto();
            List<Pme00EmployeeTask> pme00EmployeeTasks = pme00EmployeeTaskList.stream().filter(pme00EmployeeTask -> pme00EmployeeTask.getTaskName().equals(m00Task.getTaskName()) && pme00EmployeeTask.getProjectNumber().equals(m00Task.getProjectNumber())).collect(Collectors.toList());
            response.setTask(m00Task);
            response.setMembers(pme00EmployeeTasks);
            responseList.add(response);
        });
        return  responseList;
    }

    @Override
    public M00TaskDto modify(ServiceLifecycle serviceLifecycle, M00TaskDto newTaskRequest) {
        //convert json to DTO
        M00Task requestTask = newTaskRequest.getTask();

        // find exitedTask
        M00TaskId requestUpdatedTask = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(requestUpdatedTask));

        List<Pme00EmployeeTask> pme00EmployeeTasksList = newTaskRequest.getMembers();


        //TODO: add biz for Pme00EmployeeTask
        /*
            List<Pme00EmployeeTask> pme00EmployeeTask = new ArrayList<>();
            List<Object> pme00EmployeeTasksList = objectMapper.convertValue(newTaskRequest.get(MEMBERS_KEY), List.class);

            pme00EmployeeTasksList.forEach(pme00EmployeeTask1 -> {
                pme00EmployeeTask.add(objectMapper.convertValue(pme00EmployeeTask1, Pme00EmployeeTask.class));
            });
            //insert task
            M00Task newM00TaskRegisted = this.serviceLifecycle.requestTaskService().register(newTask);

            //inset to task member
            List<Pme00EmployeeTask> pme00EmployeeTaskResponse = this.serviceLifecycle.requestPme00EmployeeTaskService().createFromList(pme00EmployeeTask);
         */

        if (existedTask.isPresent()) {
            // find existedEmplTask

            // modify info task
            existedTask.get().setCategory(requestTask.getCategory());
            existedTask.get().setTaskExplain(requestTask.getTaskExplain());

            // TODO: add more column

            // save DB
            M00Task updatedTask = serviceLifecycle.requestTaskService().modify(requestTask);
            M00TaskDto responseUpdateTask = new M00TaskDto();
            responseUpdateTask.setMembers(pme00EmployeeTasksList);
            responseUpdateTask.setTask(updatedTask);
            return responseUpdateTask;
        }

        return null;
    }

    @Override
    public void modifyList(ServiceLifecycle serviceLifecycle, List<M00Task> entityList) {

    }

    @Override
    public M00Task register(ServiceLifecycle serviceLifecycle, M00Task entity) {
        return null;
    }

    @Override
    public void remove(ServiceLifecycle serviceLifecycle, M00TaskId id) {

    }

}
