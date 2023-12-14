package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Level2TaskLogic implements Level2TaskService {
    @Override
    public M00TaskDto findTaskByProjectNumberAndTaskName(ServiceLifecycle serviceLifecycle, M00TaskId searchTaskId) {
        Optional<M00Task> m00Task = Optional.ofNullable(
                serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(searchTaskId));
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
    public List<M00TaskDto> findAll(ServiceLifecycle serviceLifecycle, String projectNumber) {
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestTaskService().findAll(projectNumber);
        //findAllEmplTask
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByProjectMumber(projectNumber);

        List<M00TaskDto> responseList = new ArrayList<>();
        //append member to task
        m00TaskDtoList.forEach(m00Task -> {
            M00TaskDto response = new M00TaskDto();
            List<Pme00EmployeeTask> pme00EmployeeTasks = pme00EmployeeTaskList.stream()
                    .filter(pme00EmployeeTask -> pme00EmployeeTask.getTaskName().equals(m00Task.getTaskName())
                            && pme00EmployeeTask.getProjectNumber().equals(m00Task.getProjectNumber()))
                    .collect(Collectors.toList());
            response.setTask(m00Task);
            response.setMembers(pme00EmployeeTasks);
            responseList.add(response);
        });
        return responseList;
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
    public M00TaskDto register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask) {
        //check this already existed yet?
        M00TaskId newTaskId = new M00TaskId(newTask.getTask().getProjectNumber(), newTask.getTask().getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(newTaskId));
        if (existedTask.isPresent()) {
            return null;
        } else {
            // map M00TaskDto -> Jpo
            M00Task newTaskJpo = newTask.getTask();
            M00Task savedTask = serviceLifecycle.requestTaskService().register(newTaskJpo);

            // map Emp
            List<Pme00EmployeeTask> newPme00EmployeeTaskList = newTask.getMembers();

            List<Pme00EmployeeTask> savedPme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().createFromList(newPme00EmployeeTaskList);

            M00TaskDto newReponse = new M00TaskDto();
            newReponse.setTask(savedTask);
            newReponse.setMembers(savedPme00EmployeeTaskList);

            return newReponse;
        }
    }

    @Override
    public void remove(ServiceLifecycle serviceLifecycle, M00TaskId requestDeleteTaskId) {
        //check this already existed yet?
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(requestDeleteTaskId));
        if (existedTask.isPresent()) {
            //find empTask
            List<Pme00EmployeeTask> pme00EmployeeTaskExistedList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestDeleteTaskId);
            if (!pme00EmployeeTaskExistedList.isEmpty()) {
                //remove pmeEmployeeTask
                serviceLifecycle.requestPme00EmployeeTaskService().removeMultipleEmployeeTaskByTaskId(requestDeleteTaskId.getProjectNumber(), requestDeleteTaskId.getTaskName());
            }
            //Delete task
            serviceLifecycle.requestTaskService().remove(requestDeleteTaskId);
        }
    }
}
