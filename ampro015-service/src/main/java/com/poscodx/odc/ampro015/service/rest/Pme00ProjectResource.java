package com.poscodx.odc.ampro015.service.rest;

<<<<<<< HEAD
import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
=======
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
>>>>>>> 202284_lam
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Project")
@RequiredArgsConstructor
public class Pme00ProjectResource {
    private final ServiceLifecycle serviceLifecycle;

    /**
     *
     * @param projectList
     * @return
     */
    @CrossOrigin
    @PostMapping("/search")
    public List<Pme00ProjectListDto> findProjectList(@RequestBody Pme00ProjectListDto projectList) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Project -> " + projectList, this);
        String cdV = projectList.getCdV();
        int status = projectList.getStatus();
        return this.serviceLifecycle.requestLevel2Service().findAssetList(serviceLifecycle, cdV);
    }

    private final ServiceLifecycle serviceLifecycle;

    @CrossOrigin
    @PostMapping("")
    public Pme00ProjectListDto register(@RequestBody Pme00ProjectListDto dto){
        return this.serviceLifecycle.requestLevel2Service().registerProject(serviceLifecycle, dto);
    }

    @CrossOrigin
    @PutMapping("")
    public Pme00ProjectListDto modify(@RequestBody Pme00ProjectListDto dto){
        return this.serviceLifecycle.requestLevel2Service().modifyProject(serviceLifecycle, dto);
    }
}
