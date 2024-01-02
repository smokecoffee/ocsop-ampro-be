package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * SQL get project list
 *
 * @author : 202301_Duyen
 * @since : 2023-11-28
 */
public interface Pme00ProjectInfoRepository extends JpaRepository<Pme00ProjectInfoJpo, String> {

    @Query(value = "SELECT\n" +
                "E.EMP_ID,\n" +
                "E.NAME,\n" +
                "E.PHOTO\n" +
                "FROM\n" +
                "TB_M00_EMPLOYEE AS E\n" +
                "WHERE\n" +
                "E.END_DATE IS NULL", nativeQuery = true)
    List<Object[]> getActiveEmployee();

    @Query(value =
            "SELECT INFO.*\n" +
                ", EMP1.NAME AS KOREA_PM_NAME\n" +
                ", EMP1.PHOTO AS KOREA_PM_PHOTO\n" +
                ", EMP2.NAME AS VIETNAM_PL_NAME\n" +
                ", EMP2.PHOTO AS VIETNAM_PL_PHOTO\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN TB_M00_EMPLOYEE EMP1\n" +
                "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
            "JOIN TB_M00_EMPLOYEE EMP2\n" +
                "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
            "JOIN TB_M00_CODES030 CODE030\n" +
                "ON CODE030.CD_V = INFO.CD_V \n" +
            "WHERE  1= 1 \n" +
                "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                "AND (:meaning IS NULL OR (CODE030.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                "AND (:status IS NULL OR (INFO.STATUS = :status))\n" +
                "AND ( :startDate IS NULL OR (INFO.START_DATE > :startDate))\n" +
                "AND ( :endDate IS NULL OR (INFO.END_DATE <= :endDate))"
            , nativeQuery = true)
    List<Object[]> findProjectInfo(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                                              @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                              @Param("framework") String framework, @Param("status") String status,
                                              @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);


    @Query(value = "SELECT CD_V\n" +
            ",CD_V_MEANING\n" +
            ",CD_V_EXPLAIN AS CD_V_COLOR\n" +
            "from VI_M00_CODE_ACCESS\n" +
            "WHERE CD_TP = 'TASK_STATUS'\n" +
            "AND CATEGORY_GROUP_NM = 'EN0000'", nativeQuery = true)
    List<Object[]> getTaskStatus();

    @Query(value =
            "SELECT DISTINCT \n" +
                "EMP.EMP_ID \n" +
                ", EMP.NAME \n" +
                ", EMP.PHOTO \n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE AS EMP\n" +
            "ON \n" +
                "INFO.KOREA_PM = EMP.EMP_ID", nativeQuery = true)
    List<Object[]> getKoreaPM();

    @Query(value =
            "SELECT DISTINCT \n" +
                "EMP.EMP_ID \n" +
                ", EMP.NAME \n" +
                ", EMP.PHOTO \n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE AS EMP\n" +
            "ON \n" +
                "INFO.VIETNAM_PL = EMP.EMP_ID", nativeQuery = true)
    List<Object[]> getVietnamPL();

    @Query(value =
            "SELECT COUNT(*)\n" +
                    "FROM \n" +
                    "TB_PME00_PROJECT_INFO AS INFO\n" +
                    "JOIN TB_M00_CODES030 CODE\n" +
                    "ON CODE.CD_V = INFO.CD_V\n" +
                    "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
                    "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
                    "WHERE  1= 1 \n" +
                    "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                    "AND (:meaning IS NULL OR (CODE.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                    "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                    "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                    "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                    "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                    "AND (:status IS NULL OR (INFO.STATUS = :status))\n" +
                    "AND ( :startDate IS NULL OR (INFO.START_DATE >= :startDate))\n" +
                    "AND ( :endDate IS NULL OR (INFO.END_DATE <= :endDate))"
            , nativeQuery = true)
    int countProject(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                                   @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                   @Param("framework") String framework, @Param("status") String status,
                                   @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
