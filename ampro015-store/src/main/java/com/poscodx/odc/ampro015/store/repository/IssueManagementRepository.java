package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, IssueManagementId> {
    @Query(value = "SELECT * FROM TB_M00_ISSUE_MANAGEMENT  WHERE SEQ = :seq AND SITE LIKE :site", nativeQuery = true)
    List<IssueManagementJpo> findBySeqAndSite(@Param("seq") int seq, @Param("site") String site);

    @Query(value =
            "SELECT\n" +
                    " Count(*)\n" +
                    "FROM \n" +
                    "TB_M00_ISSUE_MANAGEMENT AS ISSUE\n" +
                    "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = ISSUE.REQUESTER_ID \n" +
                    "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = ISSUE.DEVELOPER \n" +
                    "WHERE  1 = 1 \n" +
                    "AND (:contents IS NULL OR (ISSUE.CONTENTS LIKE CONCAT('%', :contents, '%')))\n" +
                    "AND (:site IS NULL OR (ISSUE.SITE LIKE CONCAT('%', :site, '%')))\n" +
                    "AND (:module IS NULL OR (ISSUE.MODULE LIKE CONCAT('%', :module, '%')))\n" +
                    "AND (:division_flag IS NULL OR (ISSUE.DIVISION_FLAG LIKE CONCAT('%', :division_flag, '%')))\n" +
                    "AND (:applied_period_flag IS NULL OR (ISSUE.APPLIED_PERIOD_FLAG LIKE CONCAT('%', :applied_period_flag, '%')))\n" +
                    "AND (:accept_flag IS NULL OR (ISSUE.ACCEPT_FLAG LIKE CONCAT('%', :accept_flag, '%')))\n" +
                    "AND (:requester_confirm IS NULL OR (ISSUE.REQUESTER_CONFIRM LIKE CONCAT('%', :requester_confirm, '%')))\n" +
                    "AND (:requester IS NULL OR (ISSUE.REQUESTER LIKE CONCAT('%', :requester, '%')))\n" +
                    "AND (:requester_id IS NULL OR (ISSUE.REQUESTER_ID = :requester_id))\n" +
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND ((:fromStartDate IS NULL) OR (to_char(ISSUE.REGISTRATION_DATE) >= concat(:fromStartDate , ' 00:00:00')))\n" +
                    "AND ((:toStartDate IS NULL) OR (to_char(ISSUE.REGISTRATION_DATE) <= concat(:toStartDate , ' 99:99:99')))\n" +
                    "AND ((:fromEndDate IS NULL) OR (to_char(ISSUE.REQUEST_DATE_NEW) >= concat(:fromEndDate, ' 00:00:00')))\n" +
                    "AND ((:toEndDate IS NULL) OR (to_char(ISSUE.REQUEST_DATE_NEW) <= concat(:toEndDate, ' 99:99:99')))"
            , nativeQuery = true)
    int countIssueReport(@Param("contents") String contents,
                         @Param("site") String site,
                         @Param("module") String modules,
                         @Param("division_flag") String division_flag,
                         @Param("applied_period_flag") String applied_period_flag,
                         @Param("accept_flag") String accept_flag,
                         @Param("requester_confirm") String requester_confirm,
                         @Param("requester") String requester,
                         @Param("requester_id") String requester_id,
                         @Param("contents_kr") String contents_kr,
                         @Param("developer") String developer,
                         @Param("fromStartDate") String registrationFromStartDate, @Param("toStartDate") String registrationToEndDate,
                         @Param("fromEndDate") String requestFromStartDate, @Param("toEndDate") String requestToEndDate);

    @Query(value =
            "SELECT ISSUE.*\n" +
//                            ", EMP1.NAME AS KOREA_PM_NAME\n" +
                    ", EMP2.NAME AS DEV_NAME\n" +
                    ", EMP1.PHOTO AS PHOTO\n" +
//                            ", EMP2.PHOTO AS VIETNAM_PL_PHOTO\n" +
                    "FROM \n" +
                    "TB_M00_ISSUE_MANAGEMENT AS ISSUE\n" +
                    "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = ISSUE.REQUESTER_ID \n" +
                    "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = ISSUE.DEVELOPER \n" +
                    "WHERE 1 = 1 \n" +
                    "AND (:contents IS NULL OR (ISSUE.CONTENTS LIKE CONCAT('%', :contents, '%')))\n" +
                    "AND (:site IS NULL OR (ISSUE.SITE LIKE CONCAT('%', :site, '%')))\n" +
                    "AND (:module IS NULL OR (ISSUE.MODULE LIKE CONCAT('%', :module, '%')))\n" +
                    "AND (:division_flag IS NULL OR (ISSUE.DIVISION_FLAG LIKE CONCAT('%', :division_flag, '%')))\n" +
                    "AND (:applied_period_flag IS NULL OR (ISSUE.APPLIED_PERIOD_FLAG LIKE CONCAT('%', :applied_period_flag, '%')))\n" +
                    "AND (:accept_flag IS NULL OR (ISSUE.ACCEPT_FLAG LIKE CONCAT('%', :accept_flag, '%')))\n" +
                    "AND (:requester_confirm IS NULL OR (ISSUE.REQUESTER_CONFIRM LIKE CONCAT('%', :requester_confirm, '%')))\n" +
                    "AND (:requester IS NULL OR (ISSUE.REQUESTER LIKE CONCAT('%', :requester, '%')))\n" +
                    "AND (:requester_id IS NULL OR (ISSUE.REQUESTER_ID = :requester_id))\n" +
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND ((:fromStartDate IS NULL) OR (to_char(ISSUE.REGISTRATION_DATE) >= concat(:fromStartDate , ' 00:00:00')))\n" +
                    "AND ((:toStartDate IS NULL) OR (to_char(ISSUE.REGISTRATION_DATE) <= concat(:toStartDate , ' 99:99:99')))\n" +
                    "AND ((:fromEndDate IS NULL) OR (to_char(ISSUE.REQUEST_DATE_NEW) >= concat(:fromEndDate, ' 00:00:00')))\n" +
                    "AND ((:toEndDate IS NULL) OR (to_char(ISSUE.REQUEST_DATE_NEW) <= concat(:toEndDate, ' 99:99:99')))"
            , nativeQuery = true)
    List<Object[]> findIssueInfo(
                                @Param("contents") String contents,
                                 @Param("site") String site,
                                 @Param("module") String modules,
                                 @Param("division_flag") String division_flag,
                                 @Param("applied_period_flag") String applied_period_flag,
                                 @Param("accept_flag") String accept_flag,
                                 @Param("requester_confirm") String requester_confirm,
                                 @Param("requester") String requester,
                                 @Param("requester_id") String requester_id,
                                 @Param("contents_kr") String contents_kr,
                                 @Param("developer") String developer,
                                 @Param("fromStartDate") String registrationFromStartDate, @Param("toStartDate") String registrationToEndDate,
                                 @Param("fromEndDate") String requestFromStartDate, @Param("toEndDate") String requestToEndDate,
                                 Pageable pageable
    );

    @Query(value = "SELECT MAX(SEQ) FROM tb_m00_issue_management ", nativeQuery = true )
    int maxSeq();
}
