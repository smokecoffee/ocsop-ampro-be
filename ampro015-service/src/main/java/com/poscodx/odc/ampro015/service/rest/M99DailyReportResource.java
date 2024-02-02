package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/daily-report")
@RequiredArgsConstructor
public class M99DailyReportResource {
    private final ServiceLifecycle serviceLifecycle;
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('GET_REPORT,GET_REPORT_OWNER')")
    public Map<String, Object> findDailyReportList(@RequestBody M99DailyReport dto,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageSize") int pageSize) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().findDailyReport(dto, pageNo, pageSize);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADD_REPORT')")
    public M99DailyReport register(@RequestParam ("data") String dtoString,
                                   @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {

        return this.serviceLifecycle
                   .requestM99DailyReportService()
                   .register(serviceLifecycle, M99DailyReport.fromJson(dtoString), fileUpload);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('UPDATE_REPORT,UPDATE_REPORT_OWNER')")
    public M99DailyReport modify(@RequestParam ("data") String dtoString,
                                 @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {

        return this.serviceLifecycle
                   .requestM99DailyReportService()
                   .modify(serviceLifecycle, M99DailyReport.fromJson(dtoString), fileUpload);
    }

    @GetMapping("/working-time")
    @PreAuthorize("hasAnyAuthority('GET_REPORT,GET_REPORT_OWNER')")
    public List<M99DailyReport> findWorkingTime(@RequestParam(required = false, name = "empId") String empId,
                                                @RequestParam(required = false, name = "startTime") String startTime,
                                                @RequestParam(required = false, name = "endTime") String endTime) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().findWorkingTime(empId, startTime, endTime);
    }
}

