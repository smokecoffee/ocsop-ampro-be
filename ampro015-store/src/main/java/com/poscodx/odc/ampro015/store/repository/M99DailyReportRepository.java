package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M99DailyReportJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface M99DailyReportRepository extends JpaRepository<M99DailyReportJpo, Integer> {

    @Query(value =
        "SELECT\n" +
            " DR.*,\n" +
            " EMP.NAME,\n" +
            " EMP.PHOTO\n" +
        "FROM\n" +
            "tb_m99_daily_report AS DR\n" +
        "JOIN tb_m00_employee AS EMP\n" +
            "ON DR.EMPLOYEE_ID = EMP.EMP_ID\n" +
        "WHERE 1= 1\n" +
            "AND (:employeeId IS NULL OR :employeeId = '' OR :employeeId = DR.EMPLOYEE_ID)\n" +
            "AND (:projectNumber IS NULL OR :projectNumber = '' OR (DR.PROJECT_NUMBER LIKE CONCAT('%', :projectNumber, '%')))\n" +
            "AND ((:fromDate IS NULL) OR (DR.REGISTER_DATE >= :fromDate))\n" +
            "AND ((:toDate IS NULL) OR  (DR.REGISTER_DATE <= :toDate))"
        , nativeQuery = true)
    List<Object[]> findDailyReport(@Param("employeeId") String employeeId, @Param("projectNumber") String projectNumber,
                                            @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);

    @Query(value =
        "SELECT\n" +
             "*\n" +
        "FROM\n" +
             "tb_m99_daily_report AS DR\n" +
        "JOIN tb_m00_employee AS EMP\n" +
             "ON DR.EMPLOYEE_ID = EMP.EMP_ID\n" +
        "WHERE 1= 1\n" +
             "AND (:employeeId IS NULL OR :employeeId = '' OR :employeeId = DR.EMPLOYEE_ID)\n" +
             "AND (:projectNumber IS NULL OR :projectNumber = '' OR (DR.PROJECT_NUMBER LIKE CONCAT('%', :projectNumber, '%')))\n" +
             "AND ((:fromDate IS NULL) OR (DR.REGISTER_DATE >= :fromDate))\n" +
             "AND ((:toDate IS NULL) OR  (DR.REGISTER_DATE <= :toDate))\n" +
             "AND ((:startTime IS NULL) OR :startTime = '' OR  (DR.OP_SAT_DT >= :startTime))\n" +
             "AND ((:endTime IS NULL) OR :endTime = '' OR  (DR.OP_DN_DT <= :endTime))\n" +
             "ORDER BY DR.OP_SAT_DT ASC"
        , nativeQuery = true)
    List<M99DailyReportJpo> findWorkingTime(@Param("employeeId") String employeeId,@Param("startTime") String startTime,@Param("endTime") String endTime, @Param("projectNumber") String projectNumber,
                                            @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
}

