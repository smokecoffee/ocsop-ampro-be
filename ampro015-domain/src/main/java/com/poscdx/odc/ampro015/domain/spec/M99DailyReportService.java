package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface M99DailyReportService {

    M99DailyReport find(int seq);

    List<M99DailyReport> findAll();

    M99DailyReport modify(ServiceLifecycle serviceLifecycle, M99DailyReport dto, MultipartFile fileUpload);

    M99DailyReport register(ServiceLifecycle serviceLifecycle, M99DailyReport dto, MultipartFile fileUpload);

    void remove(ServiceLifecycle serviceLifecycle, int seq);

    List<M99DailyReport> findWorkingTime(String empId,  String startTime, String endTime);

    Map<String, Object> findDailyReport(M99DailyReport m99DailyReport, int pageNo, int pageSize);
}
