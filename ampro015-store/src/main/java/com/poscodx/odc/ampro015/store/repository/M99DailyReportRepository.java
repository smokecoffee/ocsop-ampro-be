package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M99DailyReportJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface M99DailyReportRepository extends JpaRepository<M99DailyReportJpo, Integer> {

    List<M99DailyReportJpo> findByEmployeeIdAndOpSatDateIsNotNullAndOpDnDateIsNotNull(String employeeId);
}

