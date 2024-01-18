package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementIdJpo;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, IssueManagementId> {
    @Query(value = "select * from tb_m00_issue_management tmim where seq like '%' and site like '%'",nativeQuery = true)
    List<IssueManagementJpo> findBySeqAndSite(int seq,String site);
    @Query(value = "select * from tb_m00_issue_management tmim where contents like '%'",nativeQuery = true)
    List<IssueManagementJpo> findIssueInfo(String content);

    @Query(value = "select * from tb_m00_issue_management tmim",nativeQuery = true)
    List<IssueManagementJpo> findAllRecord();
}
