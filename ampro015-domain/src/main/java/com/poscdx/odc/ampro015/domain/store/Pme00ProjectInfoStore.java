package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Pme00ProjectInfoStore
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
public interface Pme00ProjectInfoStore {
    Pme00ProjectInfo retrieve(String cdVId);

    List<Pme00ProjectInfo> retrieveAll();

    Pme00ProjectInfo update(Pme00ProjectInfo entity);

    Pme00ProjectInfo create(Pme00ProjectInfo entity);

    void delete (String cdVId);

    List<Object[]> getActiveEmployee();

    List<Object[]> findProjectInfo(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, String status,
                                   Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, Pageable pageable);

    List<Object[]> getKoreaPM();

    List<Object[]> getVietnamPL();

    List<Object[]> getProjectMember(String cdV);

    int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                     String framework, String status, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate);
}
