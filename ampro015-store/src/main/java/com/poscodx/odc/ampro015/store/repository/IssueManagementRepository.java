package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, IssueManagementId> {
    @Query(value = "select * from tb_m00_issue_management tmim where seq like '%' and site like '%'",nativeQuery = true)
    List<IssueManagementJpo> findBySeqAndSite(int seq,String site);
    @Query(value = "select * from tb_m00_issue_management tmim where contents like '%'",nativeQuery = true)
    List<IssueManagementJpo> findIssueInfo(String content);

    @Query(value = "select * from tb_m00_issue_management tmim",nativeQuery = true)
    List<IssueManagementJpo> findAllRecord();

    @Query(value = "SELECT t.SITE \n" +
            ",t.MODULE \n" +
            ",t.DIVISION_FLAG \n" +
            ",t.APPLIED_PERIOD_FLAG \n" +
            ",t.ACCEPT_FLAG \n" +
            ",t.REQUEST_CONFIRM \n" +
            ",t.REQUESTER \n" +
            ",t.CONTENTS \n" +
            ",t.CONTENTS_KR \n" +
            ",t.DEVELOPER \n" +
            "FROM tb_m00_issue_management t \n" +
            "WHERE 1=1 \n" +
            "AND (:site IS NULL OR t.SITE LIKE :site)\n"+
            "AND (:module IS NULL OR t.MODULE LIKE :module)\n"+
            "AND (:division_flag IS NULL OR t.DIVISION_FLAG LIKE :division_flag)\n"+
            "AND (:applied_period_flag IS NULL OR t.APPLIED_PERIOD_FLAG LIKE :applied_period_flag)\n"+
            "AND (:accept_flag IS NULL OR t.ACCEPT_FLAG LIKE :accept_flag)\n"+
            "AND (:request_confirm IS NULL OR t.REQUEST_CONFIRM LIKE :request_confirm)\n"+
            "AND (:requester IS NULL OR t.REQUESTER LIKE :requester)\n"+
            "AND (:site IS NULL OR t.SITE LIKE :site)\n"+
            "AND (:site IS NULL OR t.SITE LIKE :site)\n"+
            "AND (:site IS NULL OR t.SITE LIKE :site)\n", nativeQuery = true)
    List<IssueManagementJpo> searchIssue(@Param("site") String site,
                                         @Param("module") String module,
                                         @Param("division_flag") String division_flag,
                                         @Param("applied_period_flag") String applied_period_flag,
                                         @Param("accept_flag") String accept_flag,
                                         @Param("request_confirm") String request_confirm,
                                         @Param("requester") String requester,
                                         @Param("contents") String contents,
                                         @Param("contents_kr") String contents_kr,
                                         @Param("developer") String developer);
}
