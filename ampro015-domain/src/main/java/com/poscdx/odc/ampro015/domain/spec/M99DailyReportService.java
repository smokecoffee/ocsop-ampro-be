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

    M99DailyReport modify(ServiceLifecycle serviceLifecycle, M99DailyReport dto,
                          MultipartFile imageUpload, MultipartFile fileUpload);

    void modifyByList(List<M99DailyReport> entityList);

    M99DailyReport register(ServiceLifecycle serviceLifecycle, M99DailyReport dto,
                            MultipartFile imageUpload, MultipartFile fileUpload);

    void remove(int seq);

    List<M99DailyReport> findWorkingTimeByEmployeeId(String empId);

    Map<String, Object> findDailyReport(M99DailyReport m99DailyReport, int pageNo, int pageSize);
}
