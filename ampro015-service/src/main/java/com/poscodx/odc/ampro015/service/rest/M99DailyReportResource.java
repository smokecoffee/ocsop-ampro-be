package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/daily-report")
@RequiredArgsConstructor
public class M99DailyReportResource {
    private final ServiceLifecycle serviceLifecycle;
    @CrossOrigin
    @PostMapping("/search")
    public Map<String, Object> findDailyReportList(@RequestBody M99DailyReport dto,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageSize") int pageSize) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().findDailyReport(dto, pageNo, pageSize);
    }

    @CrossOrigin
    @PostMapping("")
    public M99DailyReport register(@RequestParam ("data") String dtoString,
                                   @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {

        return this.serviceLifecycle
                   .requestM99DailyReportService()
                   .register(serviceLifecycle, M99DailyReport.fromJson(dtoString), fileUpload);
    }

    @CrossOrigin
    @PutMapping("")
    public M99DailyReport modify(@RequestParam ("data") String dtoString,
                                 @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {

        return this.serviceLifecycle
                   .requestM99DailyReportService()
                   .modify(serviceLifecycle, M99DailyReport.fromJson(dtoString), fileUpload);
    }

    @CrossOrigin
    @GetMapping("/working-time")
    public List findWorkingTimeByEmployeeId(@RequestParam(required = true, name = "empId") String empId) {
        return this.serviceLifecycle.requestM99DailyReportService().findWorkingTimeByEmployeeId(empId);
    }
}
