package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;

import java.util.List;

public interface M99DailyReportService {

    M99DailyReport find(int seq);

    List<M99DailyReport> findAll();

    M99DailyReport modify(M99DailyReport requestUpdateTask);

    void modifyByList(List<M99DailyReport> entityList);

    M99DailyReport register(M99DailyReport entity);

    void remove(int seq);

    List<M99DailyReport> findWorkingTimeByEmployeeId(String empId);
}
