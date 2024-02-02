package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;

import java.util.List;

public interface ProjectManagementStore {

    List<ProjectManagementDto> getProjectList(ProjectManagementDto dto);

}
