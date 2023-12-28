package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName;
import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByProjectNumber(projectNumber);

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

        List<Pme00EmployeeTask> pme00EmployeeTasksRequestList = updateTaskRequest.getMembers();

        if (existedTask.isPresent()) {
            String existedOwnerTaskId = existedTask.get().getEmpId();
            String existedPasswordTask = existedTask.get().getPassword();
            String requestOwnerTaskId = requestTask.getEmpId();
            String requestPasswordTask = requestTask.getPassword();

            if (!existedOwnerTaskId.equals(requestOwnerTaskId) && !existedPasswordTask.equals(requestPasswordTask)) { // Don't need to check password
                return null;
            }

            // find existedEmplTask
            M00TaskId requestTaskId = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
            if (updateTaskRequest.getMembers().isEmpty()) {
                remove(serviceLifecycle, requestTaskId);
            } else {
                List<Pme00EmployeeTask> pme00EmployeeTaskExistedList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestTaskId);
                if (!pme00EmployeeTaskExistedList.isEmpty()) {
                    removeMultipleEmployeeTask(serviceLifecycle, pme00EmployeeTaskExistedList);
                }
                //insert new emplTaskList
                serviceLifecycle.requestPme00EmployeeTaskService().createFromList(updateTaskRequest.getMembers());
            }

            // modify info task
            requestTask.setPassword(encodePasswordByBase64(existedPasswordTask));
            requestTask.setEmpId(existedOwnerTaskId);

            // save DB
            M00Task updatedTask = serviceLifecycle.requestTaskService().modify(requestTask);
            M00TaskDto responseUpdateTask = new M00TaskDto();
            responseUpdateTask.setMembers(pme00EmployeeTasksRequestList);
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
            newTaskJpo.setPassword(encodePasswordByBase64(newTask.getTask().getPassword()));
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
                removeMultipleEmployeeTask(serviceLifecycle, pme00EmployeeTaskExistedList);
            }
            //Delete task
            serviceLifecycle.requestTaskService().remove(requestDeleteTaskId);
        }
    }

    /**
     * This function gets all tasks and its associated employeeTask based on condition search
     *
     * @param serviceLifecycle
     * @param projectNumber (required)
     * @param taskName
     * @param planDate
     * @param actualEndDate
     * @param pageNo
     * @param pageSize
     * @param sortBy (required)
     * @param sortDirection (required)
     * @return
     */
    @Override
    public List<M00TaskDto> findTaskByConditions(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName,
                                                 String planDate, String actualEndDate, String status, String taskOwnerId,
                                                 String category, int pageNo, int pageSize, String sortBy,
                                                 String sortDirection) {
        //create pageable
        Pageable pageable = createPageable(pageNo, pageSize, sortBy, sortDirection);
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestTaskService().findTaskByConditions(projectNumber, taskName,
                planDate, actualEndDate, status, taskOwnerId, category, pageable);

        List<M00TaskDto> responseList = new ArrayList<>();

        //append member to task
        if (!m00TaskDtoList.isEmpty()) {
            return taskManipulate(serviceLifecycle, projectNumber, m00TaskDtoList, responseList);
        }
        return responseList;
    }

    private List<M00TaskDto> taskManipulate(ServiceLifecycle serviceLifecycle, String projectNumber, List<M00Task> m00TaskDtoList, List<M00TaskDto> responseList) {
        //findAllEmplTask
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByProjectNumber(projectNumber);
        //append member to task
        m00TaskDtoList.forEach(m00Task -> {
            M00TaskDto response = new M00TaskDto();
            List<Pme00EmployeeTask> pme00EmployeeTasks = pme00EmployeeTaskList.stream().
                    filter(pme00EmployeeTask -> pme00EmployeeTask.getTaskName().equals(m00Task.getTaskName())
                            && pme00EmployeeTask.getProjectNumber().equals(m00Task.getProjectNumber()))
                    .collect(Collectors.toList());
            response.setTask(m00Task);
            response.setMembers(pme00EmployeeTasks);
            responseList.add(response);
        });
        return responseList;
    }

    private Pageable createPageable(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Optional<M00TaskJpoComlumnName> columnSort = M00TaskJpoComlumnName.getColumnName(sortBy);
        String columnName = columnSort.isPresent() ? columnSort.get().getFieldName() : "";
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(columnName).ascending()
                : Sort.by(columnName).descending();

        //create pageable
        return PageRequest.of(pageNo, pageSize, sort);
    }

    public void removeMultipleEmployeeTask(ServiceLifecycle serviceLifecycle, List<Pme00EmployeeTask> pme00EmployeeTasksRequestList) {
        pme00EmployeeTasksRequestList.forEach(pme00EmployeeTask -> {
            M00EmployeeTaskId deleteM00EmployeeTaskId = new M00EmployeeTaskId(pme00EmployeeTask.getProjectNumber(),
                    pme00EmployeeTask.getTaskName(), pme00EmployeeTask.getEmpId());
            serviceLifecycle.requestPme00EmployeeTaskService().remove(deleteM00EmployeeTaskId);
        });
    }

    @Override
    public List<M00TaskDto> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, String projectNumber,
                                                 String taskName, String status, String employeeId) {
        List<Object[]> employeeTaskList = serviceLifecycle.requestTaskService().findAllEmployeeId(projectNumber, taskName, status, employeeId);
        List<M00TaskDto> m00TaskDtoList = new ArrayList<>();

        if (employeeTaskList.isEmpty()) {
            return new ArrayList<M00TaskDto>();
        } else {
            for (Object[] obj : employeeTaskList) {
                M00TaskDto newM00TaskDto = new M00TaskDto();
                M00Task task = new M00Task(obj);
                Pme00EmployeeTask member = new Pme00EmployeeTask(obj);

                newM00TaskDto.setTask(task);
                newM00TaskDto.setMembers(Arrays.asList(member));
                m00TaskDtoList.add(newM00TaskDto);
            }
            return m00TaskDtoList;
        }
    }

    private String encodePasswordByBase64(String requestPassword) {
        return Base64.getEncoder().withoutPadding().encodeToString(requestPassword.getBytes());
    }
}
