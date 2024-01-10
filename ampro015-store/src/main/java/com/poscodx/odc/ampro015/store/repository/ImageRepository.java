package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageJpo, Integer> {

//    @Query(value = "SELECT *\n"+
//            "FROM tb_a01_image i\n" +
//            "WHERE 1=1\n" +
//            "AND i.ASSET_ID = :assetId\n" +
//            "AND i.DELETE_AT IS NULL", nativeQuery = true)
//    Iterable<ImageJpo> findAllByAssetId(@Param("assetId") Integer assetId);

    List<ImageJpo> findByAssetId(int assetId);

    List<ImageJpo> findByAssetIdAndDeleteAtIsNull(int assetId);

}
