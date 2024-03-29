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
    @Query(value =
            "SELECT INFO.*\n" +
                ", EMP1.NAME AS KOREA_PM_NAME\n" +
                ", EMP1.PHOTO AS KOREA_PM_PHOTO\n" +
                ", EMP2.NAME AS VIETNAM_PL_NAME\n" +
                ", EMP2.PHOTO AS VIETNAM_PL_PHOTO\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO INFO\n" +
            "JOIN TB_M00_EMPLOYEE EMP1\n" +
                "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
            "JOIN TB_M00_EMPLOYEE EMP2\n" +
                "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
            "JOIN TB_M00_CODES030 CODE030\n" +
                "ON CODE030.CD_V = INFO.CD_V \n" +
            "WHERE  1 = 1 \n" +
                "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                "AND (:meaning IS NULL OR (CODE030.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                "AND (:searchAllStatus = TRUE OR INFO.STATUS IN (:searchStatus))\n" +
                "AND ((:fromStartDate IS NULL AND :toStartDate IS NULL) OR (INFO.START_DATE BETWEEN :fromStartDate AND :toStartDate))\n" +
                "AND ((:fromEndDate IS NULL AND :toEndDate IS NULL) OR (INFO.END_DATE BETWEEN :fromEndDate AND :toEndDate))"
            , nativeQuery = true)
    List<Object[]> findProjectInfo(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                                              @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                              @Param("framework") String framework, @Param("searchAllStatus") boolean searchAllStatus,
                                              @Param("searchStatus") List<String> searchStatus, @Param("fromStartDate") Date fromStartDate,
                                              @Param("toStartDate") Date toStartDate, @Param("fromEndDate") Date fromEndDate,
                                              @Param("toEndDate") Date toEndDate, Pageable pageable);

    @Query(value =
            "SELECT DISTINCT INFO.*\n" +
                ", EMP1.NAME AS KOREA_PM_NAME\n" +
                ", EMP1.PHOTO AS KOREA_PM_PHOTO\n" +
                ", EMP2.NAME AS VIETNAM_PL_NAME\n" +
                ", EMP2.PHOTO AS VIETNAM_PL_PHOTO\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO INFO\n" +
                "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
                "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
                "JOIN TB_M00_CODES030 CODE030\n" +
                    "ON CODE030.CD_V = INFO.CD_V \n" +
                "JOIN TB_PME00_MEMBER MEMBER\n" +
                    "ON INFO.CD_V = MEMBER.CD_V_ID \n" +
            "WHERE  1 = 1 \n" +
                "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                "AND (:meaning IS NULL OR (CODE030.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                "AND (:searchAllStatus = TRUE OR INFO.STATUS IN (:searchStatus))\n" +
                "AND ((:fromStartDate IS NULL AND :toStartDate IS NULL) OR (INFO.START_DATE BETWEEN :fromStartDate AND :toStartDate))\n" +
                "AND ((:fromEndDate IS NULL AND :toEndDate IS NULL) OR (INFO.END_DATE BETWEEN :fromEndDate AND :toEndDate))" +
                "AND (:empId IS NULL OR (INFO.KOREA_PM = :empId OR INFO.VIETNAM_PL = :empId OR :empId = MEMBER.EMP_ID))\n"
            , nativeQuery = true)
    List<Object[]> findProjectInfoWithEmpId(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                                   @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                   @Param("framework") String framework, @Param("searchAllStatus") boolean searchAllStatus,
                                   @Param("searchStatus") List<String> searchStatus, @Param("fromStartDate") Date fromStartDate,
                                   @Param("toStartDate") Date toStartDate, @Param("fromEndDate") Date fromEndDate,
                                   @Param("toEndDate") Date toEndDate, @Param("empId") String empId, Pageable pageable);

    @Query(value =
            "SELECT DISTINCT \n" +
                "EMP.EMP_ID \n" +
                ", EMP.NAME \n" +
                ", EMP.PHOTO \n" +
                ", EMP.BIRTH_DATE\n" +
                ", EMP.IP_ADDRESS\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE AS EMP\n" +
            "ON \n" +
                "INFO.KOREA_PM = EMP.EMP_ID\n" +
            "WHERE \n" +
                "(:projectNumber IS NULL OR INFO.CD_V = :projectNumber)", nativeQuery = true)
    List<Object[]> getKoreaPM(@Param("projectNumber") String projectNumber);

    @Query(value =
            "SELECT DISTINCT \n" +
                "EMP.EMP_ID \n" +
                ", EMP.NAME \n" +
                ", EMP.PHOTO \n" +
                ", EMP.BIRTH_DATE\n" +
                ", EMP.IP_ADDRESS\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
            "JOIN \n" +
                "TB_M00_EMPLOYEE AS EMP\n" +
            "ON \n" +
                "INFO.VIETNAM_PL = EMP.EMP_ID\n" +
            "WHERE \n" +
                "(:projectNumber IS NULL OR INFO.CD_V = :projectNumber)", nativeQuery = true)
    List<Object[]> getVietnamPL(@Param("projectNumber") String projectoNumber);

    @Query(value =
            "SELECT E.EMP_ID \n" +
                    ", E.NAME \n" +
                    ", E.PHOTO \n" +
                    ", E.BIRTH_DATE\n" +
                    ", E.IP_ADDRESS\n" +
            "FROM \n" +
                    "TB_M00_EMPLOYEE AS E\n" +
            "WHERE E.EMP_ID IN \n" +
                "(SELECT M.EMP_ID \n" +
                        "FROM TB_PME00_MEMBER AS M \n" +
                        "WHERE M.CD_V_ID = :cdV) \n", nativeQuery = true)
    List<Object[]> getMember(@Param("cdV") String cdV);

    @Query(value =
            "SELECT Count(*)\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO AS INFO\n" +
                "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
                "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
                "JOIN TB_M00_CODES030 CODE030\n" +
                    "ON CODE030.CD_V = INFO.CD_V \n" +
            "WHERE  1 = 1 \n" +
                "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                "AND (:meaning IS NULL OR (CODE030.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                "AND (:searchAllStatus = TRUE OR INFO.STATUS IN (:searchStatus))\n" +
                "AND ((:fromStartDate IS NULL AND :toStartDate IS NULL) OR (INFO.START_DATE BETWEEN :fromStartDate AND :toStartDate))\n" +
                "AND ((:fromEndDate IS NULL AND :toEndDate IS NULL) OR (INFO.END_DATE BETWEEN :fromEndDate AND :toEndDate))"
            , nativeQuery = true)
    int countProject(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                                   @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                                   @Param("framework") String framework, @Param("searchAllStatus") boolean searchAllStatus,
                                   @Param("searchStatus") List<String> searchStatus, @Param("fromStartDate") Date fromStartDate,
                                   @Param("toStartDate") Date toStartDate, @Param("fromEndDate") Date fromEndDate,
                                   @Param("toEndDate") Date toEndDate);

    @Query(value =
            "SELECT COUNT(DISTINCT INFO.CD_V)\n" +
            "FROM \n" +
                "TB_PME00_PROJECT_INFO INFO\n" +
                "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = INFO.KOREA_PM \n" +
                "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = INFO.VIETNAM_PL \n" +
                "JOIN TB_M00_CODES030 CODE030\n" +
                    "ON CODE030.CD_V = INFO.CD_V \n" +
                "JOIN TB_PME00_MEMBER MEMBER\n" +
                    "ON INFO.CD_V = MEMBER.CD_V_ID \n" +
            "WHERE  1 = 1 \n" +
                 "AND (:cdV IS NULL OR (INFO.CD_V LIKE CONCAT('%', :cdV, '%')))\n" +
                 "AND (:meaning IS NULL OR (CODE030.CD_V_MEANING LIKE CONCAT('%', :meaning, '%')))\n" +
                 "AND (:period IS NULL OR :period = 0 OR (INFO.PERIOD = :period))\n" +
                 "AND (:koreaPM IS NULL OR ( INFO.KOREA_PM LIKE CONCAT('%', :koreaPM, '%')))\n" +
                 "AND (:vietnamPL IS NULL OR (INFO.VIETNAM_PL LIKE CONCAT('%', :vietnamPL, '%')))\n" +
                 "AND (:framework IS NULL OR (INFO.FRAMEWORK LIKE CONCAT('%', :framework, '%')))\n" +
                 "AND (:searchAllStatus = TRUE OR INFO.STATUS IN (:searchStatus))\n" +
                 "AND ((:fromStartDate IS NULL AND :toStartDate IS NULL) OR (INFO.START_DATE BETWEEN :fromStartDate AND :toStartDate))\n" +
                 "AND ((:fromEndDate IS NULL AND :toEndDate IS NULL) OR (INFO.END_DATE BETWEEN :fromEndDate AND :toEndDate))" +
                 "AND (:empId IS NULL OR :empId = MEMBER.EMP_ID)\n"
            , nativeQuery = true)
    int countProjectWithEmpId(@Param("cdV") String cdV, @Param("meaning") String meaning, @Param("period") int period,
                     @Param("koreaPM") String koreaPM,  @Param("vietnamPL") String vietnamPL,
                     @Param("framework") String framework, @Param("searchAllStatus") boolean searchAllStatus,
                     @Param("searchStatus") List<String> searchStatus, @Param("fromStartDate") Date fromStartDate,
                     @Param("toStartDate") Date toStartDate, @Param("fromEndDate") Date fromEndDate,
                     @Param("toEndDate") Date toEndDate, @Param("empId") String empId);

}
