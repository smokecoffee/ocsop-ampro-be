package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscodx.odc.ampro015.store.jpo.M00Codes030Jpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface M00Codes030Repository extends JpaRepository<M00Codes030Jpo, M00Codes030Id> {

    @Query(value = "SELECT MAX(CD_V_INQUIRY_SEQ) FROM TB_M00_CODES030 WHERE (CD_TP_ID IS NULL OR CD_TP_ID =:cdTpId) AND (CATEGORY_GROUP_ID IS NULL OR CATEGORY_GROUP_ID =:cateGroupId)", nativeQuery = true)
    int getMaxSeqInquiry(@Param("cdTpId") int cdTpId, @Param("cateGroupId") int cateGroupId);
}
