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

    /**
     * This function gets an existing tasks and its associated employeeTask based on the taskId
     *
     * @param serviceLifecycle
     * @param searchTaskId
     * @return M00TaskDto
     */
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
        return new M00TaskDto();
    }

    /**
     * This function gets all tasks and its associated employeeTask based on the project number
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @return List<M00TaskDto>
     */
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

    /**
     * This function updates task and its associated employeeTask the value based on the M00TaskDto
     *
     * @param serviceLifecycle
     * @param updateTaskRequest
     * @return M00TaskDto
     */
    @Override
    public M00TaskDto modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest) {
        //convert json to DTO
        M00Task requestTask = updateTaskRequest.getTask();
        // find exitedTask
        M00TaskId requestUpdatedTask = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(requestUpdatedTask));

        List<Pme00EmployeeTask> pme00EmployeeTasksList = updateTaskRequest.getMembers();

        if (existedTask.isPresent()) {
            // find existedEmplTask
            M00TaskId requestTaskId = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
            List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestTaskId);
            if (!pme00EmployeeTaskList.isEmpty()) {
                //modify
                serviceLifecycle.requestPme00EmployeeTaskService().modify(updateTaskRequest.getMembers());
            } else {
                //insert new emplTaskList
                serviceLifecycle.requestPme00EmployeeTaskService().createFromList(updateTaskRequest.getMembers());
            }
            // modify info task
            existedTask.get().setCategory(requestTask.getCategory());
            existedTask.get().setTaskExplain(requestTask.getTaskExplain());
            existedTask.get().setEmpId(requestTask.getEmpId());
            existedTask.get().setStatus(requestTask.getStatus());
            existedTask.get().setPlanDate(requestTask.getPlanDate());
            existedTask.get().setActualEndDate(requestTask.getActualEndDate());
            existedTask.get().setRemark(requestTask.getRemark());
            existedTask.get().setCreationTimestamp(requestTask.getCreationTimestamp());
            existedTask.get().setLastUpdateTimestamp(requestTask.getLastUpdateTimestamp());
            existedTask.get().setLastUpdateId(requestTask.getLastUpdateId());
            existedTask.get().setWriter(requestTask.getWriter());
            existedTask.get().setPassword(requestTask.getPassword());

            // save DB
            M00Task updatedTask = serviceLifecycle.requestTaskService().modify(requestTask);
            M00TaskDto responseUpdateTask = new M00TaskDto();
            responseUpdateTask.setMembers(pme00EmployeeTasksList);
            responseUpdateTask.setTask(updatedTask);
            return responseUpdateTask;
        }
        return null;
    }

    /**
     * This function inserts a new task and its associated employeeTask the value based on the M00TaskDto
     *
     * @param serviceLifecycle
     * @param newTask
     * @return M00TaskDto
     */
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

    /**
     * This function removes an existing task and its associated employeeTask based on value from the M00TaskDto.
     *
     * @param serviceLifecycle
     * @param requestDeleteTaskId
     */
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
