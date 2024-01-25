package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface M00EmployeeRepository extends JpaRepository<M00EmployeeJpo, String> {

    @Query(value = "SELECT e.EMP_ID, e.NAME, e.PHOTO, e.BIRTH_DATE, e.IP_ADDRESS\n"+
            "FROM tb_m00_employee AS e\n"+
            "WHERE e.EMP_ID IN :empId", nativeQuery = true)
    List<Object[]> getEmployeeByEmployeeId(@Param("empId") Set<String> empId);

    @Query(value = "SELECT\n" +
            " E.EMP_ID\n" +
            ",E.NAME\n" +
            ",E.PHOTO\n" +
            ",E.BIRTH_DATE\n" +
            ",E.IP_ADDRESS\n" +
            " FROM\n" +
            " POSCTZN.TB_M00_EMPLOYEE AS E\n" +
            " WHERE\n" +
            " E.END_DATE IS NULL\n" +
            " ORDER BY E.EMP_ID", nativeQuery = true)

    List<Object[]> getActiveEmployee();

    @Query(value = "SELECT E.*\n" +
            ",CODE030.CD_V_MEANING\n" +
            " FROM\n" +
            " TB_M00_EMPLOYEE AS E\n" +
            " JOIN TB_M00_CODES030 CODE030\n" +
            " ON CODE030.CD_V = E.SITE_CODE \n" +
            " WHERE 1=1\n" +
            " AND CODE030.CD_TP_ID = 51", nativeQuery = true)
    List<Object[]> findAllEmployee();

    @Query(value = "SELECT E.*\n" +
            ",CODE1.CD_V_MEANING AS SITE\n" +
            ",CODE2.CD_V_MEANING AS STATUS\n" +
            " FROM\n" +
            " TB_M00_EMPLOYEE AS E\n" +
            " JOIN TB_M00_CODES030 CODE1\n" +
            " ON CODE1.CD_V = E.SITE_CODE \n" +
            " JOIN TB_M00_CODES030 CODE2\n" +
            " ON CODE2.CD_V = E.EMP_STATUS \n" +
            " WHERE 1=1\n" +
            " AND CODE1.CD_TP_ID = 51"+
            " AND CODE2.CD_TP_ID = 99"+
            " AND (:site IS NULL OR :site = '' OR (CODE1.CD_V_EXPLAIN =:site))"+
            " AND (:status IS NULL OR :status = '' OR (CODE2.CD_V = :status))"+
            " AND (:name IS NULL OR :name = '' OR (E.NAME =:name))"+
            " AND (:empId IS NULL OR :empId = '' OR (E.EMP_ID =:empId))"+
            " AND (:joinDateFrom IS NULL OR E.JOIN_DATE >= :joinDateFrom)\n"+
            " AND (:joinDateTo IS NULL OR E.JOIN_DATE <= :joinDateTo)\n"
            , nativeQuery = true)
    List<Object[]> searchPmeEmployee(@Param("site") String site,
                                     @Param("status") String status,
                                     @Param("name") String name,
                                     @Param("empId") String empId,
                                     @Param("joinDateFrom") String joinDateFrom,
                                     @Param("joinDateTo") String joinDateTo);
    Boolean existsByName(String username);

    Boolean existsByMail(String email);

    Optional<M00EmployeeJpo> findByName(String userName);

    List<M00EmployeeJpo> findAllByEmpIdIn(List<String> id);

    @Query(value = "SELECT e.EMP_ID, e.NAME, e.PHOTO, e.BIRTH_DATE, e.IP_ADDRESS, e.MAIL\n"+
            "FROM tb_m00_employee AS e\n"+
            "WHERE e.MAIL = :email ", nativeQuery = true)
    List<Object[]> getEmployeeByEmail(@Param("email") String email);

}
