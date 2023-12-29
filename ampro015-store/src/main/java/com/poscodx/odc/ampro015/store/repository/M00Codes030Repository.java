package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import com.poscodx.odc.ampro015.store.jpo.M00Codes030Jpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface M00Codes030Repository extends JpaRepository<M00Codes030Jpo, M00Codes030Id> {

    @Query(value = "SELECT MAX(CD_V_INQUIRY_SEQ) FROM TB_M00_CODES030 WHERE (CD_TP_ID IS NULL OR CD_TP_ID =:cdTpId) AND (CATEGORY_GROUP_ID IS NULL OR CATEGORY_GROUP_ID =:cateGroupId)", nativeQuery = true)
    int getMaxSeqInquiry(@Param("cdTpId") int cdTpId, @Param("cateGroupId") int cateGroupId);

    @Query(value =
            "SELECT *\n" +
            "FROM \n" +
                "TB_M00_CODES030 \n" +
            "WHERE  1= 1 \n" +
                "AND (:cdV IS NULL OR (CD_V LIKE CONCAT('%', :cdV)))\n" +
                "AND (:meaning IS NULL OR (CD_V_MEANING LIKE CONCAT('%', :meaning)))", nativeQuery = true)
    List<M00Codes030Jpo> findM00Codes030(@Param("cdV") String cdV, @Param("meaning") String meaning);

    @Query(value =
            "SELECT *\n" +
                    "FROM \n" +
                    "TB_M00_CODES030 \n" +
                    "WHERE  1= 1 \n" +
                    "AND (CD_TP_ID =:cdTpId)\n"
                    , nativeQuery = true)
    List<M00Codes030Jpo> findM00Codes030ById(@Param("cdTpId") int cdTpId);
}
