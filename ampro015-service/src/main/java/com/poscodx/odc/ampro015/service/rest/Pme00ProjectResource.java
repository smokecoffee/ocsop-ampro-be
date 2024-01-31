package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Router API for project list Class
 *
 * @author : 202301_Duyen
 * @since : 2023-11-30
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class Pme00ProjectResource {
    private final ServiceLifecycle serviceLifecycle;

    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('GET_PROJECT,GET_PROJECT_OWNER')")
    public Map<String, Object> findProjectList(@RequestBody ProjectManagementDto dto,
                                               @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                               @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Project -> " + dto, this);
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectList(serviceLifecycle, dto, pageNo, pageSize);
    }

    @PostMapping("/search-include-task")
    @PreAuthorize("hasAnyAuthority('VIEW_PROJECT_MONITORING')")
    public Map<String, Object> findProjectListWithTask(@RequestBody ProjectManagementDto dto,
                                               @RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                               @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Project -> " + dto, this);
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectListWithTask(serviceLifecycle, dto, pageNo, pageSize);
    }

    @GetMapping(path = "/monitoring")
    @PreAuthorize("hasAnyAuthority('GET_PROJECT,GET_PROJECT_OWNER')")
    public Map<String, Object> findAllProjectMonitoring(@RequestParam(required = false, defaultValue = "0", name = "pageNo") int pageNo,
                                                        @RequestParam(required = false, defaultValue = "20", name = "pageSize") int pageSize) {
        if (Utils.checkPermission("GET_PROJECT_OWNER")) {
            String id = Utils.getLoginUserDetail();
            if (id != null) {
                return this.serviceLifecycle.requestLevel2ProjectService().getProjectListWithEmpId(serviceLifecycle, id, pageNo, pageSize);
            }
        }
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectListWithEmpId(serviceLifecycle, null, pageNo, pageSize);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADD_PROJECT')")
    public boolean register(@RequestParam ("data") String dtoString,
                            @RequestParam (value = "imageUpload", required = false) MultipartFile imageUpload,
                            @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {

        return this.serviceLifecycle
                   .requestLevel2ProjectService()
                   .registerProject(serviceLifecycle, ProjectManagementDto.fromJson(dtoString), imageUpload, fileUpload);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('UPDATE_PROJECT,UPDATE_PROJECT_OWNER')")
    public boolean modify(@RequestParam ("data") String dtoString,
                          @RequestParam (value = "imageUpload", required = false) MultipartFile imageUpload,
                          @RequestParam (value = "fileUpload", required = false) MultipartFile fileUpload) throws SQLException {
        return this.serviceLifecycle
                   .requestLevel2ProjectService()
                   .modifyProject(serviceLifecycle, ProjectManagementDto.fromJson(dtoString), imageUpload, fileUpload);
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('DELETE_PROJECT,DELETE_PROJECT_OWNER')")
    public boolean delete(@RequestBody M00Codes030Id id) throws SQLException {
        return this.serviceLifecycle.requestLevel2ProjectService().deleteProject(serviceLifecycle, id);
    }

    @GetMapping("/search-pm-pl/{type}")
    public List<M00Employee> getKoreaPM (@PathVariable("type") String type,
                                         @RequestParam(value = "projectNumber", required = false) String projectNumber) {
        if (type.equals("pm")){
            return this.serviceLifecycle.requestPme00ProjectInfoService().getKoreaPM(projectNumber);
        }
        else if (type.equals("pl")) {
            return this.serviceLifecycle.requestPme00ProjectInfoService().getVietnamPL(projectNumber);
        }
        else
            return new ArrayList<>();

    }

    @GetMapping("/search-member/{cdV}")
    public List<M00Employee> findProjectMember(@PathVariable("cdV") String cdV) {
        return this.serviceLifecycle.requestLevel2ProjectService().getProjectMember(serviceLifecycle, cdV);
    }
}
