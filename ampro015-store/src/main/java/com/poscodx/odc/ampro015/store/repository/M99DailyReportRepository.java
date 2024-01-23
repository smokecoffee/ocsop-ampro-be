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
            "AND ((:fromDate IS NULL AND :toDate IS NULL) OR ((:fromDate = '' AND :toDate = '')) OR (DR.REGISTER_DATE BETWEEN :fromDate AND :toDate))"
        , nativeQuery = true)
    List<Object[]> findDailyReport(@Param("employeeId") String employeeId, @Param("projectNumber") String projectNumber,
                                            @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);

    @Query(value =
        "SELECT\n" +
             " Count(*)\n" +
        "FROM\n" +
             "tb_m99_daily_report AS DR\n" +
        "JOIN tb_m00_employee AS EMP\n" +
             "ON DR.EMPLOYEE_ID = EMP.EMP_ID\n" +
        "WHERE 1= 1\n" +
             "AND (:employeeId IS NULL OR :employeeId = '' OR :employeeId = DR.EMPLOYEE_ID)\n" +
             "AND (:projectNumber IS NULL OR :projectNumber = '' OR (DR.PROJECT_NUMBER LIKE CONCAT('%', :projectNumber, '%')))\n" +
             "AND ((:fromDate IS NULL AND :toDate IS NULL) OR ((:fromDate = '' AND :toDate = '')) OR (DR.REGISTER_DATE BETWEEN :fromDate AND :toDate))"
        , nativeQuery = true)
    int countDailyReport(@Param("employeeId") String employeeId, @Param("projectNumber") String projectNumber,
                         @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    List<M99DailyReportJpo> findByEmployeeIdAndOpSatDateIsNotNullAndOpDnDateIsNotNull(String employeeId);
}

