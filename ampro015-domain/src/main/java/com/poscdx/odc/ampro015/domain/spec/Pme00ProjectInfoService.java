package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.sql.SQLException;
import java.util.List;

public interface Pme00ProjectInfoService {
    Pme00ProjectInfo find(String cdVId);

    List<Pme00ProjectInfo> findAll();

    void modify(List<Pme00ProjectInfo> pme00ProjectInfo);

    Pme00ProjectInfo register(Pme00ProjectInfo entity);

    void remove(String cdVId);

    List<EmployeeDto> getActiveEmployee();

    Pme00ProjectListDto registerProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto) throws SQLException;

    Pme00ProjectListDto modifyProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto) throws SQLException;

    void deleteProject(ServiceLifecycle serviceLifecycle, M00Codes030Id id) throws SQLException;

    List<Pme00ProjectListDto> getProjectList (ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto);
}
