package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.logic.Level2Logic;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Level2TaskLogic implements Level2TaskService {
    @Override
    public M00Task find(ServiceLifecycle serviceLifecycle, M00TaskId id) {
        return null;
    }

    @Override
    public Map<String, Object> findAll(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName, String planDate, String actualEndDate, int pageNo, int pageSize, String sortBy, String sortDirection) {
        //findAllTask
        List<M00Task> m00TaskDtoList = serviceLifecycle.requestTaskService().findAll(projectNumber, taskName, planDate, actualEndDate, pageNo, pageSize, sortBy, sortDirection);
        //findAllEmplTask
        M00TaskId requestTaskId = new M00TaskId(projectNumber, taskName);
        List<Pme00EmployeeTask> pme00EmployeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAllByTaskId(requestTaskId);

        Map<String, Object> response = new HashMap<>();
        response.put("task", m00TaskDtoList);
        response.put("members", pme00EmployeeTaskList);

        return  response;
    }

    @Override
    public void modify(ServiceLifecycle serviceLifecycle, List<M00Task> entityList) {

    }

    @Override
    public M00Task register(ServiceLifecycle serviceLifecycle, M00Task entity) {
        return null;
    }

    @Override
    public void remove(ServiceLifecycle serviceLifecycle, M00TaskId id) {

    }
//    public Level2TaskLogic(M00TaskStore m00TaskStore, Pme00EmployeeTaskStore pme00EmployeeTaskStore) {
//        super(m00TaskStore, pme00EmployeeTaskStore);
//    }


}
