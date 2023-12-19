package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;

import java.util.ArrayList;
import java.util.List;

public class Pme00ProjectInfoLogic implements Pme00ProjectInfoService {
    private final Pme00ProjectInfoStore store;

    public Pme00ProjectInfoLogic(Pme00ProjectInfoStore store) {
        this.store = store;
    }

    @Override
    public Pme00ProjectInfo find(String cdVId) {
        return this.store.retrieve(cdVId);
    }

    @Override
    public List<Pme00ProjectInfo> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00ProjectInfo> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00ProjectInfo register(Pme00ProjectInfo entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(String cdVId) {
        this.store.delete(cdVId);
    }

    @Override
    public List<EmployeeDto> getActiveEmployee() {
        List<Object[]> resultList = this.store.getActiveEmployee();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            employeeDtoList.add(new EmployeeDto(obj));
        }
        return employeeDtoList;
    }

    @Override
    public List<TaskStatusDto> getTaskStatus() {
        List<Object[]> resultList = this.store.getTaskStatus();
        List<TaskStatusDto> taskStatusDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            taskStatusDtoList.add(new TaskStatusDto(obj));
        }
        return taskStatusDtoList;
    }
}
