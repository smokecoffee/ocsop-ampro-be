package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository<FieldJpo, Integer> {

    @Query(value = "SELECT *\n"+
            "FROM tb_a01_field f\n" +
            "WHERE 1=1\n" +
            "AND f.ASSET_ID = :assetId\n" +
            "AND f.DELETE_AT IS NULL", nativeQuery = true)
    Iterable<FieldJpo> findAllByAssetId(@Param("assetId") Integer assetId);
}
