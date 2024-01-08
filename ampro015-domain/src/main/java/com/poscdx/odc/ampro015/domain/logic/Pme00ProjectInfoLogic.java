package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Pme00ProjectInfoLogic
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
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
    public List<EmployeeDto> getProjectMember(String cdV) {
        List<Object[]> resultList = this.store.getProjectMember(cdV);
        List<EmployeeDto> memberList = new ArrayList<>();
        for (Object[] obj : resultList) {
            memberList.add(new EmployeeDto(obj));
        }
        return memberList;
    }

    @Override
    public List<Pme00ProjectInfo> findProjectInfo(String cdV,
                                                  String meaning,
                                                  int period,
                                                  String koreaPM,
                                                  String vietnamPL,
                                                  String framework,
                                                  String status,
                                                  Date fromStartDate,
                                                  Date toStartDate,
                                                  Date fromEndDate,
                                                  Date toEndDate, Pageable pageable){
        List<Object[]> resultList = this.store.findProjectInfo(cdV, meaning, period, koreaPM, vietnamPL, framework, status, fromStartDate, toStartDate, fromEndDate, toEndDate, pageable);
        List<Pme00ProjectInfo> pme00ProjectInfoList = new ArrayList<>();
        for(Object[] obj : resultList){
            pme00ProjectInfoList.add(new Pme00ProjectInfo(obj));
        }

        return pme00ProjectInfoList;
    }

    @Override
    public List<EmployeeDto> getKoreaPM() {
        List<Object[]> resultList = this.store.getKoreaPM();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            employeeDtoList.add(new EmployeeDto(obj));
        }
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> getVietnamPL() {
        List<Object[]> resultList = this.store.getVietnamPL();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            employeeDtoList.add(new EmployeeDto(obj));
        }
        return employeeDtoList;
    }

    @Override
    public int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, String status,
                               Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate){
        return this.store.getCountProject(cdV, meaning, period, koreaPM, vietnamPL, framework, status, fromStartDate, toStartDate, fromEndDate, toEndDate);
    }

//    @Override
//    public List<Pme00ProjectInfo> findProjectInfo(String cdV, int period, String koreaPM, String vietnamPL, String framework, String status, Date startDate, Date endDate) {
//
//        List<Object[]> resultList = this.store.findProjectInfo(cdV, meaning, period, koreaPM, vietnamPL, framework, status, startDate, endDate, pageable);
//        List<Pme00ProjectInfo> pme00ProjectInfoList = new ArrayList<>();
//        for(Object[] obj : resultList){
//            pme00ProjectInfoList.add(new Pme00ProjectInfo(obj));
//        }
//
//        return pme00ProjectInfoList;
//    }
}
