package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Level2ProjectService {

    List<Object> registerProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto,
                            MultipartFile imageUpload, MultipartFile fileUpload) throws SQLException;

    List<Object> modifyProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto,
                          MultipartFile imageUpload, MultipartFile fileUpload) throws SQLException;

    List<Object> deleteProject(ServiceLifecycle serviceLifecycle, M00Codes030Id id) throws SQLException;

    Map<String, Object> getProjectList (ServiceLifecycle serviceLifecycle, ProjectManagementDto dto, int pageNo, int pageSize);

    Map<String, Object> getProjectListWithTask (ServiceLifecycle serviceLifecycle, ProjectManagementDto dto, int pageNo, int pageSize);

    Map<String, Object> getProjectListWithEmpId (ServiceLifecycle serviceLifecycle, String empId, int pageNo, int pageSize);

    List<M00Employee> getProjectMember (ServiceLifecycle serviceLifecycle, String cdV);

    ProjectManagementDto findProjectById(ServiceLifecycle serviceLifecycle, M00Codes030Id id);

}