package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeMeetingJpo;
import com.poscodx.odc.ampro015.store.jpo.Pme00RoleUserJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Pme00RoleUserRepository extends JpaRepository<Pme00RoleUserJpo, Integer> {
    @Query(value = "SELECT t.ID \n" +
            ",t.EMPLOYEE_ID \n" +
            ",t.ROLE_ID \n" +
            "FROM tb_pme00_role_user as t \n "+
            "JOIN tb_m00_employee as p on t.EMPLOYEE_ID = p.EMP_ID \n"+
            "WHERE t.EMPLOYEE_ID = :emp_id \n", nativeQuery = true)
    List<Pme00RoleUserJpo> findRoleUserByEmpId(@Param("emp_id") String empId);

    List<Pme00RoleUserJpo> findByRoleId(int roleId);

    List<Pme00RoleUserJpo> findByEmpId(String empId);
}
