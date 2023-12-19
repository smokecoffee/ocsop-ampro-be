package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface Pme00ProjectInfoRepository extends JpaRepository<Pme00ProjectInfoJpo, String> {

    @Query(value = "SELECT\n" +
                "E.EMP_ID,\n" +
                "E.NAME\n" +
                "FROM\n" +
                "POSCTZN.TB_M00_EMPLOYEE AS E\n" +
                "WHERE\n" +
                "E.END_DATE IS NULL", nativeQuery = true)
    List<Object[]> getActiveEmployee();

    @Query(value =
            "SELECT INFO.*\n" +
                ", EMP1.NAME AS KOREA_PM_NAME\n" +
                ", EMP2.NAME AS VIETNAM_PL_NAME\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN TB_M00_EMPLOYEE EMP1\n" +
                "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
            "JOIN TB_M00_EMPLOYEE EMP2\n" +
                "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
            "WHERE  1= 1 \n" +
                "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV)))\n" +
                "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM)))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL)))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework)))\n" +
                "AND (:status IS NULL OR (INFO.STATUS LIKE CONCAT('%', :status)))\n" +
                "AND ( :startDate IS NULL OR (INFO.START_DATE > :startDate))\n" +
                "AND ( :endDate IS NULL OR (INFO.END_DATE <= :endDate))"
            , nativeQuery = true)
    List<Object[]> findProjectInfo(@Param("cdV") String cdV, @Param("period") int period,
                                              @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                              @Param("framework") String framework, @Param("status") String status,
                                              @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = "SELECT CD_V\n" +
            ",CD_V_MEANING\n" +
            ",CD_V_EXPLAIN\n" +
            "from VI_M00_CODE_ACCESS\n" +
            "WHERE CD_TP = 'TASK_STATUS'\n" +
            "AND CATEGORY_GROUP_NM = 'EN0000'", nativeQuery = true)
    List<Object[]> getTaskStatus();
}
