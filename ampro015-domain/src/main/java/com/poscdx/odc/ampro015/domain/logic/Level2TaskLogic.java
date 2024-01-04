package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName;
import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskDto;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implements Level2TaskService
 *
 * @author 202296_Duong
 * @since 2023-11-11
 */
public class Level2TaskLogic implements Level2TaskService {

    static final String PROJECT_NUMBER_FIELD = "projectNumber";
    static final String TASK_NAME_FIELD = "taskName";
    static final String EMPLOYEE_ID_FIELD = "empId";
    static final String PASSWORD_FIELD = "password";
    static final String RESPONSE_CODE = "code";
    static final String RESPONSE_MESSAGE = "message";
    static final String RESPONSE_DATA = "data";
    static final String NOT_FOUND_RESPONSE_MESSAGE = "No task found matching the given criteria.";
    static final String DUPLICATE_RESPONSE_MESSAGE = "This task is already existed in system.";
    static final String UPDATE_SUCCESS_RESPONSE_MESSAGE = "The task updated successfully.";
    static final String DELETE_SUCCESS_RESPONSE_MESSAGE = "The task deleted successfully.";
    static final String INSERT_SUCCESS_RESPONSE_MESSAGE = "A new task has been successfully inserted.";
    static final String INVALID_PASSWORD_RESPONSE_MESSAGE = "Password incorrect!";



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
    public ResponseEntity<?> modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest) {
        // convert json to DTO
        M00Task requestTask = updateTaskRequest.getTask();
        // find exitedTask
        M00TaskId requestUpdatedTask = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(requestUpdatedTask));

        List<Pme00EmployeeTask> pme00EmployeeTasksRequestList = updateTaskRequest.getMembers();

        if (existedTask.isPresent()) {
            String existedOwnerTaskId = StringUtils.defaultIfBlank(existedTask.get().getEmpId(), StringUtils.EMPTY);
            String existedPasswordTask = StringUtils.defaultIfBlank(existedTask.get().getPassword(), StringUtils.EMPTY);
            String requestOwnerTaskId = StringUtils.defaultIfBlank(requestTask.getEmpId(), StringUtils.EMPTY);
            String requestPasswordTask = DigestUtils.md5Hex(StringUtils.defaultIfBlank(requestTask.getPassword(), StringUtils.EMPTY));


            if (!existedOwnerTaskId.equals(requestOwnerTaskId) && !existedPasswordTask.equals(requestPasswordTask)) { // Don't need to check password
                return appendResponse(HttpStatus.BAD_REQUEST, INVALID_PASSWORD_RESPONSE_MESSAGE, new M00TaskDto());
            }

            // find existedEmplTask
            M00TaskId requestTaskId = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
            if (updateTaskRequest.getMembers().isEmpty()) {
                Map<String, Object> requestId = new HashMap<>();
                requestId.put(PROJECT_NUMBER_FIELD, requestTask.getProjectNumber());
                requestId.put(TASK_NAME_FIELD, requestTask.getTaskName());
                requestId.put(EMPLOYEE_ID_FIELD, requestTask.getEmpId());
                requestId.put(PASSWORD_FIELD, requestTask.getPassword());
                remove(serviceLifecycle, requestId, false);
            } else {
                List<Pme00EmployeeTask> pme00EmployeeTaskExistedList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestTaskId);
                if (!pme00EmployeeTaskExistedList.isEmpty()) {
                    removeMultipleEmployeeTask(serviceLifecycle, pme00EmployeeTaskExistedList);
                }
                //insert new emplTaskList
                serviceLifecycle.requestPme00EmployeeTaskService().createFromList(updateTaskRequest.getMembers());
            }

            // modify info task
            requestTask.setPassword(DigestUtils.md5Hex(requestTask.getPasswordRequest()));
            requestTask.setEmpId(existedOwnerTaskId);

            // save DB
            M00Task updatedTask = serviceLifecycle.requestTaskService().modify(requestTask);
            M00TaskDto responseUpdateTask = new M00TaskDto();
            responseUpdateTask.setMembers(pme00EmployeeTasksRequestList);
            responseUpdateTask.setTask(updatedTask);
            return appendResponse(HttpStatus.OK, UPDATE_SUCCESS_RESPONSE_MESSAGE, responseUpdateTask);
        }
        return appendResponse(HttpStatus.BAD_REQUEST, NOT_FOUND_RESPONSE_MESSAGE, new M00TaskDto());
    }

    /**
     * This function inserts a new task and its associated employeeTask the value based on the M00TaskDto
     *
     * @param serviceLifecycle
     * @param newTask
     * @return M00TaskDto
     */
    @Override
    public ResponseEntity<?> register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask) {
        //check this already existed yet?
        M00TaskId newTaskId = new M00TaskId(newTask.getTask().getProjectNumber(), newTask.getTask().getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(newTaskId));
        if (existedTask.isPresent()) {
            HashMap<String, Object> mapResponse = (HashMap<String, Object>) appendResponse(HttpStatus.BAD_REQUEST, String.format(DUPLICATE_RESPONSE_MESSAGE, "task"), new M00TaskDto()).getBody();
            mapResponse.get(RESPONSE_DATA);
            return appendResponse(HttpStatus.BAD_REQUEST, DUPLICATE_RESPONSE_MESSAGE, new M00TaskDto());
        } else {
            // map M00TaskDto -> Jpo
            M00Task newTaskJpo = newTask.getTask();

            newTaskJpo.setPassword(DigestUtils.md5Hex(newTask.getTask().getPassword()));
            M00Task savedTask = serviceLifecycle.requestTaskService().register(newTaskJpo);

            // map Emp
            M00TaskDto newReponse = new M00TaskDto();
            newReponse.setTask(savedTask);
            List<Pme00EmployeeTask> newPme00EmployeeTaskList = newTask.getMembers();
            List<Pme00EmployeeTask> savedPme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().createFromList(newPme00EmployeeTaskList);
            newReponse.setMembers(savedPme00EmployeeTaskList);
            return appendResponse(HttpStatus.OK, INSERT_SUCCESS_RESPONSE_MESSAGE, newReponse);
        }
    }

    /**
     * This function removes an existing task and its associated employeeTask based on value from the M00TaskDto.
     *
     * @param serviceLifecycle
     * @param requestDeleteTaskId
     */
    @Override
    public ResponseEntity<?> remove(ServiceLifecycle serviceLifecycle, Map<String, Object> requestDeleteTaskId, boolean isCheck) {
        String requestProjectNumber = (String) requestDeleteTaskId.get(PROJECT_NUMBER_FIELD);
        String requestTaskName = (String) requestDeleteTaskId.get(TASK_NAME_FIELD);
        String requestOwnerTaskId = (String) requestDeleteTaskId.get(EMPLOYEE_ID_FIELD);
        String requestPasswordTask = (String) requestDeleteTaskId.get(PASSWORD_FIELD);
        requestPasswordTask = DigestUtils.md5Hex(requestPasswordTask);

        M00TaskId deleteTaskId = new M00TaskId(requestProjectNumber, requestTaskName);

        //check this already existed yet?
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestTaskService().findTaskByProjectNumberAndTaskName(deleteTaskId));

        if (existedTask.isPresent()) {

            if (isCheck) {
                String existedOwnerTaskId = StringUtils.defaultIfBlank(existedTask.get().getEmpId(), StringUtils.EMPTY);
                String existedPasswordTask = StringUtils.defaultIfBlank(existedTask.get().getPassword(), StringUtils.EMPTY);

                if ((StringUtils.isNotEmpty(requestOwnerTaskId) && StringUtils.isNotBlank(requestPasswordTask))
                        && (!existedOwnerTaskId.equals(requestOwnerTaskId) && !existedPasswordTask.equals(requestPasswordTask))) {
                    return appendResponse(HttpStatus.BAD_REQUEST, INVALID_PASSWORD_RESPONSE_MESSAGE, new M00TaskDto());
                }
            }
            //find empTask
            List<Pme00EmployeeTask> pme00EmployeeTaskExistedList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(deleteTaskId);

            if (!pme00EmployeeTaskExistedList.isEmpty()) {
                //remove pmeEmployeeTask
                removeMultipleEmployeeTask(serviceLifecycle, pme00EmployeeTaskExistedList);
            }
            //Delete task
            serviceLifecycle.requestTaskService().remove(deleteTaskId);
            return appendResponse(HttpStatus.OK, DELETE_SUCCESS_RESPONSE_MESSAGE, new M00TaskDto());
        }
        return appendResponse(HttpStatus.BAD_REQUEST, NOT_FOUND_RESPONSE_MESSAGE, new M00TaskDto());
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
    public ResponseEntity<?> findTaskByConditions(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName,
                                                  String planDate, String actualEndDate, String status, String taskOwnerId,
                                                  String category, int pageNo, int pageSize, String sortBy,
                                                  String sortDirection) {
        //create pageable
        Pageable pageable = createPageable(pageNo, pageSize, sortBy, sortDirection);
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestTaskService().findTaskByConditions(projectNumber, taskName,
                planDate, actualEndDate, status, taskOwnerId, category, pageable);

        Set<String> empMap = m00TaskDtoList.stream()
                .filter(m00Task -> StringUtils.isNotBlank(m00Task.getEmpId()))
                .map(M00Task::getEmpId)
                .collect(Collectors.toSet());

        List<Object[]> imgSrc = new ArrayList<>();
        Map<String, String> empIdImgMap = new HashMap<>();

        if(!empMap.isEmpty()){
            imgSrc = serviceLifecycle.requestTaskService().getImagePathByEmployeeId(empMap);
            //convert map
            empIdImgMap = convertPhotoEmployeeMap(imgSrc);
        }

        List<M00TaskDto> responseList = new ArrayList<>();

        //append member to task
        if (!m00TaskDtoList.isEmpty()) {
            List<M00TaskDto> m00TaskDtoListResponse = taskManipulate(serviceLifecycle, projectNumber, m00TaskDtoList, responseList, empIdImgMap);
            return appendResponse(HttpStatus.OK, "List task", m00TaskDtoListResponse);
        }
        return appendResponse(HttpStatus.BAD_REQUEST, NOT_FOUND_RESPONSE_MESSAGE, responseList);
    }

    /**
     * append task, member to M00TaskDto
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @param m00TaskDtoList
     * @param responseList
     * @param empIdImgMap
     * @return List<M00TaskDto>
     */
    private List<M00TaskDto> taskManipulate(ServiceLifecycle serviceLifecycle, String projectNumber,
                                            List<M00Task> m00TaskDtoList, List<M00TaskDto> responseList, Map<String, String> empIdImgMap) {
        //findAllEmplTask
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByProjectNumber(projectNumber);

        //append member to task
        m00TaskDtoList.forEach(m00Task -> {
            M00TaskDto response = new M00TaskDto();
            List<Pme00EmployeeTask> pme00EmployeeTasks = pme00EmployeeTaskList.stream().
                    filter(pme00EmployeeTask -> pme00EmployeeTask.getTaskName().equals(m00Task.getTaskName())
                            && pme00EmployeeTask.getProjectNumber().equals(m00Task.getProjectNumber()))
                    .collect(Collectors.toList());
            String emplId = m00Task.getEmpId();
            if (empIdImgMap.containsKey(emplId)) {
                response.setPhoto(empIdImgMap.get(emplId));
            } else {
                response.setPhoto(StringUtils.EMPTY);
            }
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

    /**
     * find task by employeeId function
     *
     * @param serviceLifecycle
     * @param projectNumber
     * @param taskName
     * @param status
     * @param employeeId
     * @return List<M00TaskDto>
     */
    @Override
    public List<M00TaskDto> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, String projectNumber,
                                                 String taskName, String status, String employeeId) {
        List<Object[]> employeeTaskList = serviceLifecycle.requestTaskService().findAllEmployeeId(projectNumber, taskName, status, employeeId);
        List<M00TaskDto> m00TaskDtoList = new ArrayList<>();

        if (employeeTaskList.isEmpty()) {
            return new ArrayList<M00TaskDto>();
        } else {
            List<Object[]> imgSrc = new ArrayList<>();

            if (StringUtils.isNotBlank(employeeId)) {
                imgSrc = serviceLifecycle.requestTaskService().getImagePathByEmployeeId(new HashSet<>(Arrays.asList(employeeId)));
            } else {
                imgSrc = serviceLifecycle.requestTaskService().getEmployeeImagePathAll();
            }
            //convert map
            Map<String, String> empIdImgMap = convertPhotoEmployeeMap(imgSrc);
            for (Object[] obj : employeeTaskList) {
                M00TaskDto newM00TaskDto = new M00TaskDto();
                String emplId = (String) obj[2];
                M00Task task = new M00Task(obj);
                Pme00EmployeeTask member = new Pme00EmployeeTask(obj);

                newM00TaskDto.setTask(task);
                if (empIdImgMap.containsKey(emplId)) {
                    newM00TaskDto.setPhoto(empIdImgMap.get(emplId));
                } else {
                    newM00TaskDto.setPhoto(StringUtils.EMPTY);
                }
                newM00TaskDto.setMembers(Arrays.asList(member));
                m00TaskDtoList.add(newM00TaskDto);
            }
            return m00TaskDtoList;
        }
    }

    /**
     * convert Object to Map function
     *
     * @param imgSrc
     * @return Map<String, String>
     */
    private Map<String, String> convertPhotoEmployeeMap(List<Object[]> imgSrc) {
        //convert map
        Map<String, String> empIdImgMap = new HashMap<>();
        for (Object[] imgObj : imgSrc) {
            String empId = (String) imgObj[0];
            String empImg = (String) imgObj[1];
            empIdImgMap.put(empId, empImg);
        }
        return empIdImgMap;
    }

    private ResponseEntity<?> appendResponse(HttpStatus status, String message, Object data){
        Map<String, Object> response = new HashMap<>();
        response.put(RESPONSE_CODE, status.value());
        response.put(RESPONSE_MESSAGE, message);
        response.put(RESPONSE_DATA, data);
        return new ResponseEntity<>(response, status);
    }
}
