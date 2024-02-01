package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
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
    public List<M00Employee> getProjectMember(String cdV) {
        List<Object[]> resultList = this.store.getProjectMember(cdV);
        List<M00Employee> memberList = new ArrayList<>();
        for (Object[] obj : resultList) {
            memberList.add(new M00Employee(obj));
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
                                                  List<String> searchStatus,
                                                  Date fromStartDate,
                                                  Date toStartDate,
                                                  Date fromEndDate,
                                                  Date toEndDate, Pageable pageable){

        boolean checkSearch = (searchStatus == null || searchStatus.isEmpty());

        if (searchStatus == null) {
            searchStatus = new ArrayList<>();
        }
        if(searchStatus.isEmpty()) {
            searchStatus.add("");
        }
        if(searchStatus.get(0).equals("All")) {
            checkSearch = true;
        }

        List<Object[]> resultList = this.store.findProjectInfo(cdV, meaning, period, koreaPM, vietnamPL, framework,
                !checkSearch, searchStatus, fromStartDate, toStartDate, fromEndDate,
                                                               toEndDate, pageable);
        List<Pme00ProjectInfo> pme00ProjectInfoList = new ArrayList<>();
        for(Object[] obj : resultList){
            pme00ProjectInfoList.add(new Pme00ProjectInfo(obj));
        }

        return pme00ProjectInfoList;
    }

    @Override
    public List<Pme00ProjectInfo> findProjectInfoWithEmpId(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                                                           List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId,
                                                           Pageable pageable) {

        boolean checkSearch = (searchStatus == null || searchStatus.isEmpty());

        if (searchStatus == null) {
            searchStatus = new ArrayList<>();
        }
        if(searchStatus.isEmpty()) {
            searchStatus.add("");
        }
        if(searchStatus.get(0).equals("All")) {
            checkSearch = true;
        }

        List<Object[]> resultList = this.store.findProjectInfoWithEmpId(cdV, meaning, period, koreaPM, vietnamPL, framework,
                !checkSearch, searchStatus, fromStartDate, toStartDate, fromEndDate,
                toEndDate, empId, pageable);
        List<Pme00ProjectInfo> pme00ProjectInfoList = new ArrayList<>();
        for(Object[] obj : resultList){
            pme00ProjectInfoList.add(new Pme00ProjectInfo(obj));
        }

        return pme00ProjectInfoList;
    }

    @Override
    public List<M00Employee> getKoreaPM(String projectNumber) {
        List<Object[]> resultList = this.store.getKoreaPM(projectNumber);
        List<M00Employee> m00EmployeeList = new ArrayList<>();
        for (Object[] obj : resultList) {
            m00EmployeeList.add(new M00Employee(obj));
        }
        return m00EmployeeList;
    }

    @Override
    public List<M00Employee> getVietnamPL(String projectNumber) {
        List<Object[]> resultList = this.store.getVietnamPL(projectNumber);
        List<M00Employee> m00EmployeeList = new ArrayList<>();
        for (Object[] obj : resultList) {
            m00EmployeeList.add(new M00Employee(obj));
        }
        return m00EmployeeList;
    }

    @Override
    public int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                               List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate){

        boolean checkSearch = (searchStatus == null || searchStatus.isEmpty());

        if (searchStatus == null) {
            searchStatus = new ArrayList<>();
        }
        if(searchStatus.isEmpty()) {
            searchStatus.add("");
        }
        if(searchStatus.get(0).equals("All")) {
            checkSearch = true;
        }

        return this.store.getCountProject(cdV, meaning, period, koreaPM, vietnamPL, framework, !checkSearch,
                                            searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate);
    }

    @Override
    public int getCountProjectWithEmpId(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                                        List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId) {

        boolean checkSearch = (searchStatus == null || searchStatus.isEmpty());

        if (searchStatus == null) {
            searchStatus = new ArrayList<>();
        }
        if(searchStatus.isEmpty()) {
            searchStatus.add("");
        }
        if(searchStatus.get(0).equals("All")) {
            checkSearch = true;
        }

        return this.store.getCountProjectWithEmpId(cdV, meaning, period, koreaPM, vietnamPL, framework, !checkSearch,
                                                    searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate, empId);
    }
}
