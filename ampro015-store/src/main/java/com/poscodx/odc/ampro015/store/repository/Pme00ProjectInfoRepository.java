package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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


    @Query(value = "aaa", nativeQuery = true)
    List<Object[]> getProjectList();
}
