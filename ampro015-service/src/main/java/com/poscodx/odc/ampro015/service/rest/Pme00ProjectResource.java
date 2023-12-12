package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectMonitoringDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Project")
public class Pme00ProjectResource {

    private final ServiceLifecycle service;

    public Pme00ProjectResource(ServiceLifecycler service) { this.service = service; }

    @CrossOrigin
    @GetMapping(path = "/project_monitoring/all")
    public List<Pme00ProjectMonitoringDto> findAllProjectMonitoring() {
        List<Pme00ProjectMonitoringDto> result = new ArrayList<>();

        List<Pme00ProjectInfo> projectList = this.service.requestPme00ProjectInfoService().findAll();

        if (projectList != null) {
            for (Pme00ProjectInfo pme00ProjectInfo : projectList) {

                List<M00Task> taskList = this.service.requestTaskService().findAll(pme00ProjectInfo.getCdV());

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

}
