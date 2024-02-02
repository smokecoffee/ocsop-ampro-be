package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName;
import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import feign.Util;
import lombok.var;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
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
                serviceLifecycle.requestM00TaskService().findTaskByProjectNumberAndTaskName(searchTaskId));
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
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestM00TaskService().findAll(projectNumber);
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

            List<M00Employee> plList = serviceLifecycle.requestPme00ProjectInfoService()
                    .getVietnamPL(m00Task.getProjectNumber());
            if (!plList.isEmpty()) {
                response.setPlDto(plList.get(0));
            }
            List<M00Employee> pmList = serviceLifecycle.requestPme00ProjectInfoService()
                    .getKoreaPM(m00Task.getProjectNumber());
            if (!plList.isEmpty()) {
                response.setPmDto(pmList.get(0));
            }

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
    public boolean modify(ServiceLifecycle serviceLifecycle, M00TaskDto updateTaskRequest, MultipartFile fileUpload) {
        // convert json to DTO
        M00Task requestTask = updateTaskRequest.getTask();
        // find exitedTask
        M00TaskId requestUpdatedTask = new M00TaskId(requestTask.getProjectNumber(), requestTask.getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestM00TaskService().findTaskByProjectNumberAndTaskName(requestUpdatedTask));

        List<Pme00EmployeeTask> pme00EmployeeTasksRequestList = updateTaskRequest.getMembers();

        if (existedTask.isPresent()) {
            String existedOwnerTaskId = StringUtils.defaultIfBlank(existedTask.get().getEmpId(), StringUtils.EMPTY);
            String existedPasswordTask = StringUtils.defaultIfBlank(existedTask.get().getPassword(), StringUtils.EMPTY);
            String requestOwnerTaskId = StringUtils.defaultIfBlank(requestTask.getEmpId(), StringUtils.EMPTY);
            String requestPasswordTask = DigestUtils.md5Hex(StringUtils.defaultIfBlank(requestTask.getPassword(), StringUtils.EMPTY));

            // delete old file from server
            if (requestTask.getAttach_File() == null || fileUpload != null) {
                List<String> fileList = new ArrayList<>();
                fileList.add(StringUtils.defaultIfBlank(existedTask.get().getAttach_File(), StringUtils.EMPTY));
                boolean result = serviceLifecycle.requestLevel2Service()
                        .removeFile(Utils.UPLOAD_BUCKET, "Task", fileList);
            }


            if (!existedOwnerTaskId.equals(requestOwnerTaskId) && !existedPasswordTask.equals(requestPasswordTask)) { // Don't need to check password
                return false;
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
            M00Task updatedTask = serviceLifecycle.requestM00TaskService().modify(requestTask);
            M00TaskDto responseUpdateTask = new M00TaskDto();
            responseUpdateTask.setMembers(pme00EmployeeTasksRequestList);
            responseUpdateTask.setTask(updatedTask);

            if (fileUpload != null) {
                String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Task", fileUpload);
                return result.contains("Task");
            }
            return true;
        }
        return false;
    }

    /**
     * This function inserts a new task and its associated employeeTask the value based on the M00TaskDto
     *
     * @param serviceLifecycle
     * @param newTask
     * @return M00TaskDto
     */

    @Override
    public boolean register(ServiceLifecycle serviceLifecycle, M00TaskDto newTask, MultipartFile fileUpload) {
        //check this already existed yet?
        M00TaskId newTaskId = new M00TaskId(newTask.getTask().getProjectNumber(), newTask.getTask().getTaskName());
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestM00TaskService().findTaskByProjectNumberAndTaskName(newTaskId));
        if (existedTask.isPresent()) {
            HashMap<String, Object> mapResponse = (HashMap<String, Object>) appendResponse(HttpStatus.BAD_REQUEST, String.format(DUPLICATE_RESPONSE_MESSAGE, "task"), new M00TaskDto()).getBody();
            mapResponse.get(RESPONSE_DATA);
            return false;
        } else {
            // map M00TaskDto -> Jpo
            M00Task newTaskJpo = newTask.getTask();

            newTaskJpo.setPassword(DigestUtils.md5Hex(newTask.getTask().getPassword()));
            M00Task savedTask = serviceLifecycle.requestM00TaskService().register(newTaskJpo);

            // map Emp
            M00TaskDto newReponse = new M00TaskDto();
            newReponse.setTask(savedTask);
            List<Pme00EmployeeTask> newPme00EmployeeTaskList = newTask.getMembers();
            List<Pme00EmployeeTask> savedPme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().createFromList(newPme00EmployeeTaskList);
            newReponse.setMembers(savedPme00EmployeeTaskList);

            if (fileUpload != null) {
                String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Task", fileUpload);
                return result.contains("Task");
            }

            return true;
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
        Optional<M00Task> existedTask = Optional.ofNullable(serviceLifecycle.requestM00TaskService().findTaskByProjectNumberAndTaskName(deleteTaskId));

        if (existedTask.isPresent()) {

            if (isCheck) {
                String existedOwnerTaskId = StringUtils.defaultIfBlank(existedTask.get().getEmpId(), StringUtils.EMPTY);
                String existedPasswordTask = StringUtils.defaultIfBlank(existedTask.get().getPassword(), StringUtils.EMPTY);

                if ((StringUtils.isNotEmpty(requestOwnerTaskId) && StringUtils.isNotBlank(requestPasswordTask))
                        && (!existedOwnerTaskId.equals(requestOwnerTaskId) && !existedPasswordTask.equals(requestPasswordTask))) {
                    return appendResponse(HttpStatus.BAD_REQUEST, INVALID_PASSWORD_RESPONSE_MESSAGE, new M00TaskDto());
                }
            }

            // delete old file from server
            List<String> fileList = new ArrayList<>();
            fileList.add(StringUtils.defaultIfBlank(existedTask.get().getAttach_File(), StringUtils.EMPTY));
            boolean result = serviceLifecycle.requestLevel2Service()
                    .removeFile(Utils.UPLOAD_BUCKET, "Task", fileList);


            //find empTask
            List<Pme00EmployeeTask> pme00EmployeeTaskExistedList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(deleteTaskId);

            if (!pme00EmployeeTaskExistedList.isEmpty()) {
                //remove pmeEmployeeTask
                removeMultipleEmployeeTask(serviceLifecycle, pme00EmployeeTaskExistedList);
            }
            //Delete task
            serviceLifecycle.requestM00TaskService().remove(deleteTaskId);
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
        //findProject
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestM00TaskService().findTaskByConditions(projectNumber, taskName,
                planDate, actualEndDate, status, taskOwnerId, category, pageable);

        Set<String> empMap = m00TaskDtoList.stream()
                .filter(m00Task -> StringUtils.isNotBlank(m00Task.getEmpId()))
                .map(M00Task::getEmpId)
                .collect(Collectors.toSet());

        List<Object[]> empSrc;
        List<M00Employee> m00EmployeeList = new ArrayList<>();

        if(!empMap.isEmpty()){
            empSrc = serviceLifecycle.requestM00EmployeeService().getEmployeeByEmployeeId(empMap);
            //convert map
            if (!empSrc.isEmpty()) {
                for (Object[] obj : empSrc) {
                    m00EmployeeList.add(new M00Employee(obj));
                }
            }
        }

        List<M00TaskDto> responseList = new ArrayList<>();

        //append member to task
        if (!m00TaskDtoList.isEmpty()) {
            List<M00TaskDto> m00TaskDtoListResponse = taskManipulate(serviceLifecycle, projectNumber,
                                                                    m00TaskDtoList, responseList, m00EmployeeList);
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
     * @param m00EmployeeList
     * @return List<M00TaskDto>
     */
    private List<M00TaskDto> taskManipulate(ServiceLifecycle serviceLifecycle, String projectNumber,
                                            List<M00Task> m00TaskDtoList, List<M00TaskDto> responseList,
                                            List<M00Employee> m00EmployeeList) {
        //set projectName
        List<Pme00EmployeeTask> pme00EmployeeTaskList = new ArrayList<>();

        //findAllEmpTask
        if (StringUtils.isNotEmpty(projectNumber)) {
            pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByProjectNumber(projectNumber);
        } else {
            Set<String> projectNumberSet = m00TaskDtoList.stream().map(M00Task::getProjectNumber).collect(Collectors.toSet());
            if (!projectNumberSet.isEmpty()) {
                pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllBySetProjectNumber(projectNumberSet);
            }
        }

        //append member to task
        for (M00Task m00Task : m00TaskDtoList) {
            M00TaskDto response = new M00TaskDto();
            if (!pme00EmployeeTaskList.isEmpty()) {
                List<Pme00EmployeeTask> pme00EmployeeTasks = pme00EmployeeTaskList.stream().
                        filter(pme00EmployeeTask -> pme00EmployeeTask.getTaskName().equals(m00Task.getTaskName())
                                && pme00EmployeeTask.getProjectNumber().equals(m00Task.getProjectNumber()))
                        .collect(Collectors.toList());
                response.setMembers(pme00EmployeeTasks);
            } else {
                response.setMembers(new ArrayList<>());
            }
            List<M00Employee> plList = serviceLifecycle.requestPme00ProjectInfoService()
                                                                        .getVietnamPL(projectNumber);
            if (!plList.isEmpty()) {
                response.setPlDto(plList.get(0));
            }
            List<M00Employee> pmList = serviceLifecycle.requestPme00ProjectInfoService()
                    .getKoreaPM(projectNumber);
            if (!plList.isEmpty()) {
                response.setPmDto(pmList.get(0));
            }
            Optional<M00Employee> optional;
            String empId = m00Task.getEmpId();
            optional = m00EmployeeList.stream()
                    .filter(employeeDto -> employeeDto.getEmpId().equals(empId))
                    .findFirst();
            response.setCreatorDto(optional.orElseGet(M00Employee::new));
            response.setTask(m00Task);
            responseList.add(response);
        }
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
     * @return List<M00TaskDto>
     */
    @Override
    public ResponseEntity<?> findTaskByEmployeeId(ServiceLifecycle serviceLifecycle, TaskSearchDTO taskSearchDTO) {
        String projectNumber = StringUtils.defaultIfBlank(taskSearchDTO.getProjectNumber(), null);
        String taskName = StringUtils.defaultIfBlank(taskSearchDTO.getTaskName(), null);
        String status = StringUtils.defaultIfBlank(taskSearchDTO.getStatus(), null);
        String employeeId = StringUtils.defaultIfBlank(taskSearchDTO.getEmpId(), null);

        List<Object[]> employeeTaskList = serviceLifecycle.requestM00TaskService().findAllTaskByEmpId(projectNumber, taskName, status, employeeId);
        List<M00TaskDto> m00TaskDtoList = new ArrayList<>();

        if (employeeTaskList.isEmpty()) {
            return appendResponse(HttpStatus.BAD_REQUEST, NOT_FOUND_RESPONSE_MESSAGE, new ArrayList<>());
        } else {
            List<Object[]> empSrc = new ArrayList<>();
            List<M00Employee> m00EmployeeList = new ArrayList<>();

            if (StringUtils.isNotBlank(employeeId)) {
                empSrc = serviceLifecycle.requestM00EmployeeService()
                                         .getEmployeeByEmployeeId(new HashSet<>(Collections.singletonList(employeeId)));
                if (!empSrc.isEmpty()) {
                    for (Object[] obj : empSrc) {
                        m00EmployeeList.add(new M00Employee(obj));
                    }
                }
            } else {
                m00EmployeeList = serviceLifecycle.requestM00EmployeeService().getActiveEmployee();
            }

            Optional<M00Employee> optional;
            List<M00TaskDto> responseList = new ArrayList<>();

            Map<String, String> empIdImgMap = convertPhotoEmployeeMap(empSrc);
            for (Object[] obj : employeeTaskList) {
                M00TaskDto newM00TaskDto = new M00TaskDto();
                String empId = (String) obj[2];
                M00Task task = new M00Task(obj);
                Pme00EmployeeTask member = new Pme00EmployeeTask(obj);
                newM00TaskDto.setTask(task);

                optional = m00EmployeeList.stream()
                        .filter(employeeDto -> employeeDto.getEmpId().equals(empId))
                        .findFirst();
                newM00TaskDto.setCreatorDto(optional.orElseGet(M00Employee::new));
                newM00TaskDto.setMembers(Collections.singletonList(member));
                m00TaskDtoList.add(newM00TaskDto);
            }
            return appendResponse(HttpStatus.OK, "List task", m00TaskDtoList);
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


    /**
     * This function gets all tasks and its associated employeeTask based on condition search
     *
     * @param serviceLifecycle
     * @return
     */
    @Override
    public ResponseEntity<?> searchTask(ServiceLifecycle serviceLifecycle, TaskSearchDTO searchTask) {
        //findAllTask
        String projectNumber = StringUtils.defaultIfBlank(searchTask.getProjectNumber(), null);
        String taskName = StringUtils.defaultIfBlank(searchTask.getTaskName(), null);
        String status = StringUtils.defaultIfBlank(searchTask.getStatus(), null);
        String empId = StringUtils.defaultIfBlank(searchTask.getEmpId(), null);
        String category = StringUtils.defaultIfBlank(searchTask.getCategory(), null);

        Date planFrom = ObjectUtils.defaultIfNull(searchTask.getPlanFrom(), null);
        Date planTo = ObjectUtils.defaultIfNull(searchTask.getPlanTo(), null);
        Date actualFrom = ObjectUtils.defaultIfNull(searchTask.getActualFrom(), null);
        Date actualTo = ObjectUtils.defaultIfNull(searchTask.getActualTo(), null);
        Date startDateFrom = ObjectUtils.defaultIfNull(searchTask.getStartDateFrom(), null);
        Date startDateTo = ObjectUtils.defaultIfNull(searchTask.getStartDateTo(), null);

        List<M00Task> m00TaskDtoList = serviceLifecycle.requestM00TaskService().searchTask(projectNumber, taskName,
                convertDateToString(planFrom), convertDateToString(planTo),
                convertDateToString(actualFrom), convertDateToString(actualTo),
                convertDateToString(startDateFrom), convertDateToString(startDateTo),
                status, empId, category);

        Set<String> empMap = m00TaskDtoList.stream()
                .filter(m00Task -> StringUtils.isNotBlank(m00Task.getEmpId()))
                .map(M00Task::getEmpId)
                .collect(Collectors.toSet());

        List<Object[]> empSrc;
        List<M00Employee> m00EmployeeList = new ArrayList<>();

        if(!empMap.isEmpty()){
            empSrc = serviceLifecycle.requestM00EmployeeService().getEmployeeByEmployeeId(empMap);
            //convert map
            if (!empSrc.isEmpty()) {
                for (Object[] obj : empSrc) {
                    m00EmployeeList.add(new M00Employee(obj));
                }
            }
        }

        List<M00TaskDto> responseList = new ArrayList<>();

        //append member to task
        if (!m00TaskDtoList.isEmpty()) {
            List<M00TaskDto> m00TaskDtoListResponse = taskManipulate(serviceLifecycle, projectNumber,
                                                                     m00TaskDtoList, responseList, m00EmployeeList);
            return appendResponse(HttpStatus.OK, "List task", m00TaskDtoListResponse);
        }
        return appendResponse(HttpStatus.BAD_REQUEST, NOT_FOUND_RESPONSE_MESSAGE, responseList);
    }

    @Override
    public M00Employee getCreator(ServiceLifecycle serviceLifecycle, String employeeId) {
        Set<String> idSet = new HashSet<>();
        idSet.add(employeeId);
        List<Object[]> resultList = serviceLifecycle.requestM00EmployeeService().getEmployeeByEmployeeId(idSet);
        if (!resultList.isEmpty()) {
            return new M00Employee(resultList.get(0));
        }
        return null;
    }

    private String convertDateToString(Date date){
        if (Objects.isNull(date)) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts t
            // convert
            return  LocalDate.parse(sdf.format(date)).plusDays(1).toString();
        }
    }


}
