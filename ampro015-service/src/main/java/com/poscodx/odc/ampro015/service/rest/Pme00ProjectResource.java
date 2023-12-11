package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("")
    public Pme00ProjectListDto register(@RequestBody Pme00ProjectListDto dto){
        return this.serviceLifecycle.requestPme00ProjectInfoService().registerProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @PutMapping("")
    public Pme00ProjectListDto modify(@RequestBody Pme00ProjectListDto dto){
        return this.serviceLifecycle.requestPme00ProjectInfoService().modifyProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @DeleteMapping("")
    public void delete(@RequestBody M00Codes030Id id){
        this.serviceLifecycle.requestPme00ProjectInfoService().deleteProject(serviceLifecycle, id);
    }
}
