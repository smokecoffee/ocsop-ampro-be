package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import org.springframework.data.domain.Pageable;

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

    List<M00Employee> getProjectMember(String cdV);

    List<Pme00ProjectInfo> findProjectInfo (String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                                            String framework, String status, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, Pageable pageable);

    List<M00Employee> getKoreaPM();

    List<M00Employee> getVietnamPL();

    int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL,
                     String framework, String status, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate);
}
