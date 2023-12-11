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
            "SELECT \n" +
                "PRO.CD_V_MEANING\n" +
                ", PRO.CD_V_EXPLAIN\n" +
                ", INFO.*\n" +
                ", EMP1.NAME AS KOREA_PM_NAME\n" +
                ", EMP2.NAME as VIETNAM_PL_NAME\n" +
            "FROM \n" +
                "TB_M00_CODES030 AS PRO\n" +
            "JOIN \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
                "ON PRO.CD_V = INFO.CD_V \n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE  AS EMP1\n" +
                "ON INFO.KOREA_PM  = EMP1.EMP_ID \n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE  AS EMP2\n" +
                "ON INFO.VIETNAM_PL  = EMP2.EMP_ID \n" +
            "WHERE  1= 1 \n" +
                "AND (:meaning IS NULL OR ( PRO.CD_V_MEANING LIKE CONCAT('%', :meaning)))\n" +
                "AND (:cdV IS NULL OR (PRO.CD_V LIKE CONCAT('%', :cdV)))\n" +
                "AND (:period IS NULL OR (INFO.PERIOD LIKE CONCAT('%', :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM)))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL)))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework)))\n" +
                "AND (:status IS NULL OR (INFO.STATUS LIKE CONCAT('%', :status)))\n" +
//                "AND (:startDate = '' OR (INFO.START_DATE >= :startDate))\n" +
//                "AND (:endDate = '' OR (INFO.END_DATE <= :endDate))"
            "AND (INFO.START_DATE >= :startDate)\n" +
            "AND (INFO.END_DATE <= :endDate)"
            , nativeQuery = true)
    List<Object[]> getProjectList(@Param("cdV") String cdV, @Param("meaning") String meaning,
                                  @Param("period") int period, @Param("koreaPM") String koreaPM,
                                  @Param("vietnamPL") String vietnamPL, @Param("framework") String framework,
                                  @Param("status") String status, @Param("startDate") Date startDate,
                                  @Param("endDate") Date endDate);
}
