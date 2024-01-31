package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface M99DailyReportStore {
    M99DailyReport retrieve(int seq);

    M99DailyReport update(M99DailyReport entity);

    M99DailyReport create(M99DailyReport entity);

    void delete(int seq);

    List<M99DailyReport> retrieveAll();

    List<M99DailyReport> findWorkingTime(String empId, String startTime, String endTime);

    List<Object[]> findDailyReport(String employeeId, String projectNumber, Date fromDate, Date toDate, Pageable pageable);

}
