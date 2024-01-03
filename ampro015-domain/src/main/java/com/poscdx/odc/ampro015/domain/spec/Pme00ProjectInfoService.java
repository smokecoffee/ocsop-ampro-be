package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Pme00ProjectInfoService
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
public interface Pme00ProjectInfoService {
    Pme00ProjectInfo find(String cdVId);

    List<Pme00ProjectInfo> findAll();

    void modify(List<Pme00ProjectInfo> pme00ProjectInfo);

    Pme00ProjectInfo register(Pme00ProjectInfo entity);

    void remove(String cdVId);

    List<EmployeeDto> getActiveEmployee();

    List<Pme00ProjectInfo> findProjectInfo (String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                                            String framework, String status, Date startDate, Date endDate, Pageable pageable);

    List<EmployeeDto> getKoreaPM();

    List<EmployeeDto> getVietnamPL();

    int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                     String framework, String status, Date startDate, Date endDate);
}
