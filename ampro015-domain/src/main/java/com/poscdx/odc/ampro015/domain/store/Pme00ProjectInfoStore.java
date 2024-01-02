package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface Pme00ProjectInfoStore {
    Pme00ProjectInfo retrieve(String cdVId);

    List<Pme00ProjectInfo> retrieveAll();

    Pme00ProjectInfo update(Pme00ProjectInfo entity);

    Pme00ProjectInfo create(Pme00ProjectInfo entity);

    void delete (String cdVId);

    List<Object[]> getActiveEmployee();

    List<Object[]> findProjectInfo(String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                                   String framework, String status, Date startDate, Date endDate, Pageable pageable);

    List<Object[]> getTaskStatus();

    List<Object[]> getKoreaPM();

    List<Object[]> getVietnamPL();

    int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                     String framework, String status, Date startDate, Date endDate);
//    List<Object[]> findProjectInfo(String cdV, int period, String koreaPM, String vietnamPL,
//                                           String framework, String status, Date startDate, Date endDate);
}
