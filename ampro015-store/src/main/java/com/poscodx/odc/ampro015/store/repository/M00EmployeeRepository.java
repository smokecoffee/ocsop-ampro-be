package com.poscodx.odc.ampro015.store.repository;

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

    Boolean existsByName(String username);

    Boolean existsByMail(String email);

    Optional<M00EmployeeJpo> findByName(String userName);

    List<M00EmployeeJpo> findAllByEmpIdIn(List<String> id);

}
