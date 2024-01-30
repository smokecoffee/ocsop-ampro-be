package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.S91MenuJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface S91MenuRepository extends JpaRepository<S91MenuJpo, String> {


    @Query(value =
            " SELECT *" +
            " FROM TB_S91_MENU" +
            " WHERE CATEGORY IN (:permissionList)" +
            " AND UPPER_CATEGORY = 'PME00'", nativeQuery = true)
    Iterable<S91MenuJpo> findMenuByPermission(@Param("permissionList")List<String> permissionList);
}
