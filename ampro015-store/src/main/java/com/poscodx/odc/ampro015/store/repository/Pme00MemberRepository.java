package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Pme00MemberRepository extends JpaRepository<Pme00MemberJpo, Integer> {

    @Query(value = "SELECT * FROM TB_PME00_MEMBER WHERE (:cdVId IS NULL OR CD_V_ID =:cdVId)", nativeQuery = true)
    List<Pme00MemberJpo> getListMemberByCdVId(@Param("cdVId") String cdVId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_PME00_MEMBER WHERE (:cdVId IS NULL OR CD_V_ID =:cdVId) AND (:empId IS NULL OR EMP_ID =:empId)", nativeQuery = true)
    void deleteMemberById(@Param("cdVId") String cdVId, @Param("empId") String empId);
}
