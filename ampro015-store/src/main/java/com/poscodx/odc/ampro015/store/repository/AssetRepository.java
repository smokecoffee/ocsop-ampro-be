package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.AssetJpo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<AssetJpo, Integer> {

   @Query(value = "SELECT *\n" +
           "FROM TB_A01_ASSET a\n" +
           "WHERE (:emplId IS NULL OR :emplId = '' OR a.EMP_ID = :emplId)\n" +
           "AND (:assetId IS NULL OR :assetId = 0 OR a.ID = :assetId)\n" +
           "AND a.STATUS = :status\n" +
           "AND a.DELETE_AT IS NULL", nativeQuery = true)
   List<AssetJpo> findByAssetAndOwnerAndStatus(@Param("assetId") int assetId, @Param("emplId") String emplId,
                                            @Param("status") int status);

    AssetJpo findByToken(String token);

    List<AssetJpo> findByOwnerContainingAndStatusAndDeleteAtIsNull(String owner, int status);

}
