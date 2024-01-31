package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.AssetJpo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends JpaRepository<AssetJpo, Integer> {

    @Query(value = "SELECT a.*\n" +
            ", b.NAME AS OWNER_NAME\n" +
            ", b.PHOTO AS OWNER_IMAGE \n" +
            "FROM TB_A01_ASSET a\n" +
            "INNER JOIN tb_m00_employee b\n" +
            "ON a.OWNER = b.EMP_ID \n" +
            "WHERE (:empId IS NULL OR :empId = '' OR a.OWNER = :empId)\n" +
            "AND (:assetId IS NULL OR :assetId = 0 OR a.ID = :assetId)\n" +
            "AND a.STATUS = :status\n" +
            "AND a.DELETE_AT IS NULL", nativeQuery = true)
   List<Object[]>  findByAssetAndOwnerAndStatus(@Param("assetId") int assetId, @Param("empId") String empId,
                                            @Param("status") int status);

    AssetJpo findByToken(String token);

    List<AssetJpo> findByOwnerContainingAndStatusAndDeleteAtIsNull(String owner, int status);

}
