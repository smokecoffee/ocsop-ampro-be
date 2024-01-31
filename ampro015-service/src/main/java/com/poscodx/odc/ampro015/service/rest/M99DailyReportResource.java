package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public M99DailyReport register(@RequestBody M99DailyReport dto) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().register(dto);
    }

    @CrossOrigin
    @PutMapping("")
    public M99DailyReport modify(@RequestBody M99DailyReport dto) throws SQLException {
        return this.serviceLifecycle.requestM99DailyReportService().modify(dto);
    }

    @CrossOrigin
    @GetMapping("/working-time")
    public List findWorkingTime(@RequestParam(required = false,defaultValue = "", name = "empId") String empId,
                                @RequestParam(required = false,defaultValue = "", name = "startTime") String startTime,
                                @RequestParam(required = false,defaultValue = "", name = "endTime") String endTime) throws SQLException{
        return this.serviceLifecycle.requestM99DailyReportService().findWorkingTime(empId, startTime, endTime);
    }
}
