package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface M00TaskService {
    M00Task findTaskByProjectNumberAndTaskName(M00TaskId id);

    List<M00Task> findAll(String projectNumber);

    M00Task modify(M00Task requestUpdateTask);
    void modifyByList(List<M00Task> entityList);

    M00Task register(M00Task entity);

    void remove(M00TaskId id);

    List<M00Task> findTaskByConditions(String projectNumber, String taskName, String planDate,
                                       String actualEndDate, String status, String empId, String category, Pageable pageable);

    public List<Object[]> findAllEmployeeId(String projectNumber, String taskName, String status,String employeeId);

    List<Object[]> getImagePathByEmployeeId(Set<String> empId);
    List<Object[]> getEmployeeImagePathAll();

    List<M00Task> searchTask(String projectNumber, String taskName, String planFrom, String planTo, String actualFrom,
                             String actualTo, String startDateFrom, String startDateTo, String status, String empId, String category);
}
