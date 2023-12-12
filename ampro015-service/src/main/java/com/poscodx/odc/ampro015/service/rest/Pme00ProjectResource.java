package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectMonitoringDto;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/Project")
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
    public List<Pme00ProjectListDto> findProjectList(@RequestBody Pme00ProjectListDto dto) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Project -> " + dto, this);
        return this.serviceLifecycle.requestPme00ProjectInfoService().getProjectList(serviceLifecycle, dto);
    }

    public Pme00ProjectResource(ServiceLifecycler service) { this.serviceLifecycle = service; }

    @CrossOrigin
    @GetMapping(path = "/project_monitoring/all")
    public List<Pme00ProjectMonitoringDto> findAllProjectMonitoring() {
        List<Pme00ProjectMonitoringDto> result = new ArrayList<>();

        List<Pme00ProjectInfo> projectList = this.serviceLifecycle.requestPme00ProjectInfoService().findAll();

        if (projectList != null) {
            for (Pme00ProjectInfo pme00ProjectInfo : projectList) {

                List<M00Task> taskList = this.serviceLifecycle.requestTaskService().findAll(pme00ProjectInfo.getCdV());

                Pme00ProjectMonitoringDto newObject = new Pme00ProjectMonitoringDto(pme00ProjectInfo, taskList);

                result.add(newObject);
            }
        }



        return result;
    }

//    @CrossOrigin
//    @GetMapping(path = "/find")
//    public List<Pme00ProjectDto> find(@RequestParam(required = true) String projectNumber) {
//
//    }
    @CrossOrigin
    @PostMapping("")
    public Pme00ProjectListDto register(@RequestBody Pme00ProjectListDto dto) throws SQLException {
        return this.serviceLifecycle.requestPme00ProjectInfoService().registerProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @PutMapping("")
    public Pme00ProjectListDto modify(@RequestBody Pme00ProjectListDto dto) throws SQLException {
        return this.serviceLifecycle.requestPme00ProjectInfoService().modifyProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @DeleteMapping("")
    public void delete(@RequestBody M00Codes030Id id) throws SQLException {
        this.serviceLifecycle.requestPme00ProjectInfoService().deleteProject(serviceLifecycle, id);
    }
}
