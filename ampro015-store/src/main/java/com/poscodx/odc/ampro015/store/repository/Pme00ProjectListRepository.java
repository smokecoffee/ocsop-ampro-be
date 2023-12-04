package com.poscodx.odc.ampro015.store.repository;

//import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectListRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Pme00ProjectListRepository extends JpaRepository<Pme00ProjectListRepository, String> {
    @Query(value = "WITH A AS(\n"+
            "SELECT PRO.CD_V_MEANING, PRO.CD_V_EXPLAIN,\n"+
            "INFO.*,\n"+
            "B.EMP_ID\n"+
            "FROM TB_M00_CODES030 AS PRO\n"+
            "JOIN TB_PME00_PROJECT_INFO AS INFO\n"+
            "ON PRO.CD_V = INFO.CD_V\n"+
            "JOIN TB_PME00_MEMBER AS MB\n"+
            "ON PRO.CD_V = MB.CD_V_ID\n )"+
            "SELECT A.CD_V, A.CD_V_MEANING, A.CD_V_EXPLAIN, A.PERIOD, A.KOREA_PM, A.VIETNAM_PL, A.STATUS, A.FRAMEWORK,\n"+
            "GROUP_CONCAT(A.EMP_ID SEPARATOR ', ') AS MEMBER_NAME\n"+
            "FROM A\n"+
            "GROUP BY A.CD_V", nativeQuery = true)

    Iterable<Pme00ProjectListRepository> findAllProject(@Param("owner") String owner,
                                                 @Param("status") int status);
}