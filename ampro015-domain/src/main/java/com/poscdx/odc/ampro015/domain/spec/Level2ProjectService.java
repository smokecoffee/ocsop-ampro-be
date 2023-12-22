package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.sql.SQLException;
import java.util.List;

public interface Level2ProjectService {

    boolean registerProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto) throws SQLException;

    boolean modifyProject(ServiceLifecycle serviceLifecycle, ProjectManagementDto dto) throws SQLException;

    boolean deleteProject(ServiceLifecycle serviceLifecycle, M00Codes030Id id) throws SQLException;

    List<ProjectManagementDto> getProjectList (ServiceLifecycle serviceLifecycle, ProjectManagementDto dto);

    List<ProjectManagementDto> getProjectList (ServiceLifecycle serviceLifecycle);

}
