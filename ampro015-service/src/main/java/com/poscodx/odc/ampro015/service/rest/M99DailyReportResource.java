package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
//    @PreAuthorize("hasAuthority('GET_REPORT, GET_REPORT_OWNER')")
    public Map<String, Object> findDailyReportList(@RequestBody M99DailyReport dto,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                                    @RequestParam(required = false, defaultValue = "0", name = "pageSize") int pageSize) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().findDailyReport(dto, pageNo, pageSize);
    }

    @PostMapping("")
//    @PreAuthorize("hasAuthority('ADD_REPORT')")
    public M99DailyReport register(@RequestBody M99DailyReport dto) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().register(dto);
    }

    @PutMapping("")
//    @PreAuthorize("hasAuthority('UPDATE_REPORT, UPDATE_REPORT_OWNER')")
    public M99DailyReport modify(@RequestBody M99DailyReport dto) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().modify(dto);
    }

    @GetMapping("/working-time")
//    @PreAuthorize("hasAuthority('GET_REPORT, GET_REPORT_OWNER')")
    public List<M99DailyReport> findWorkingTimeByEmployeeId(@RequestParam(required = true, name = "empId") String empId) {
        return this.serviceLifecycle.requestM99DailyReportService().findWorkingTimeByEmployeeId(empId);
    }
}
