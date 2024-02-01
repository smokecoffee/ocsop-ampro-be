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

    List<Pme00ProjectInfo> findProjectInfo (String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                                            List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, Pageable pageable);
    List<Pme00ProjectInfo> findProjectInfoWithEmpId (String cdV, String meaning, int period, String koreaPM, String vietnamPL,String framework,
                                                     List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId,
                                                     Pageable pageable);

    List<M00Employee> getKoreaPM(String projectNumber);

    List<M00Employee> getVietnamPL(String projectNumber);

    int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                        List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate);

    int getCountProjectWithEmpId(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework,
                                 List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId);
}
