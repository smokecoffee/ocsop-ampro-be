package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class Pme00ProjectResource {
    private final ServiceLifecycle serviceLifecycle;

    /**
     *
     * @param
     * @return
     */
    @CrossOrigin
    @PostMapping("/search")
    public List<ProjectManagementDto> findProjectList(@RequestBody ProjectManagementDto dto) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Project -> " + dto, this);
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectList(serviceLifecycle, dto);
    }

    @CrossOrigin
    @GetMapping(path = "/monitoring")
    public List<ProjectManagementDto> findAllProjectMonitoring() {
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectList(serviceLifecycle);
    }

    @CrossOrigin
    @PostMapping("")
    public boolean register(@RequestBody ProjectManagementDto dto) throws SQLException {
        return this.serviceLifecycle.requestLevel2ProjectService().registerProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @PutMapping("")
    public boolean modify(@RequestBody ProjectManagementDto dto) throws SQLException {
        return this.serviceLifecycle.requestLevel2ProjectService().modifyProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @DeleteMapping("")
    public void delete(@RequestBody M00Codes030Id id) throws SQLException {
        this.serviceLifecycle.requestLevel2ProjectService().deleteProject(serviceLifecycle, id);
    }

    @CrossOrigin
    @PostMapping("/search_KoreaPM")
    public List<EmployeeDto> getKoreaPM () {
        return this.serviceLifecycle.requestPme00ProjectInfoService().getKoreaPM();
    }

    @CrossOrigin
    @PostMapping("/search_VietnamPL")
    public List<EmployeeDto> getVietnamPL () {
        return this.serviceLifecycle.requestPme00ProjectInfoService().getVietnamPL();
    }
}
