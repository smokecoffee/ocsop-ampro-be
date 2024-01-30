package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND ((:fromStartDate IS NULL) OR (ISSUE.REGISTRATION_DATE >= :fromStartDate))\n" +
                    "AND ((:toStartDate IS NULL) OR (ISSUE.REGISTRATION_DATE <= :toStartDate))\n" +
                    "AND ((:fromEndDate IS NULL) OR (ISSUE.REQUEST_DATE >= :fromEndDate))" +
                    "AND ((:toEndDate IS NULL) OR (ISSUE.REQUEST_DATE <= :toEndDate))"
            , nativeQuery = true)
    int countIssueReport(@Param("contents") String contents,
                         @Param("site") String site,
                         @Param("module") String modules,
                         @Param("division_flag") String division_flag,
                         @Param("applied_period_flag") String applied_period_flag,
                         @Param("accept_flag") String accept_flag,
                         @Param("requester_confirm") String requester_confirm,
                         @Param("requester") String requester,
                         @Param("contents_kr") String contents_kr,
                         @Param("developer") String developer,
                         @Param("fromStartDate") Date registrationFromStartDate, @Param("toStartDate") Date registrationToEndDate,
                         @Param("fromEndDate") Date requestFromStartDate, @Param("toEndDate") Date requestToEndDate);

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
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND ((:fromStartDate IS NULL) OR (ISSUE.REGISTRATION_DATE >= :fromStartDate))\n" +
                    "AND ((:toStartDate IS NULL) OR (ISSUE.REGISTRATION_DATE <= :toStartDate))\n" +
                    "AND ((:fromEndDate IS NULL) OR (ISSUE.REQUEST_DATE_NEW >= :fromEndDate))" +
                    "AND ((:toEndDate IS NULL) OR (ISSUE.REQUEST_DATE_NEW <= :toEndDate))"
            , nativeQuery = true)
    List<Object[]> findIssueInfo(@Param("contents") String contents,
                                 @Param("site") String site,
                                 @Param("module") String modules,
                                 @Param("division_flag") String division_flag,
                                 @Param("applied_period_flag") String applied_period_flag,
                                 @Param("accept_flag") String accept_flag,
                                 @Param("requester_confirm") String requester_confirm,
                                 @Param("requester") String requester,
                                 @Param("contents_kr") String contents_kr,
                                 @Param("developer") String developer,
                                 @Param("fromStartDate") Date registrationFromStartDate, @Param("toStartDate") Date registrationToEndDate,
                                 @Param("fromEndDate") Date requestFromStartDate, @Param("toEndDate") Date requestToEndDate,
                                 Pageable pageable
    );

    @Query(value = "SELECT * FROM tb_m00_issue_management tmim", nativeQuery = true)
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
            "WHERE 1 = 1 \n" +
            "AND (:site IS NULL OR t.SITE LIKE :site)\n" +
            "AND (:module IS NULL OR t.MODULE LIKE :module)\n" +
            "AND (:division_flag IS NULL OR t.DIVISION_FLAG LIKE :division_flag)\n" +
            "AND (:applied_period_flag IS NULL OR t.APPLIED_PERIOD_FLAG LIKE :applied_period_flag)\n" +
            "AND (:accept_flag IS NULL OR t.ACCEPT_FLAG LIKE :accept_flag)\n" +
            "AND (:request_confirm IS NULL OR t.REQUEST_CONFIRM LIKE :request_confirm)\n" +
            "AND (:requester IS NULL OR t.REQUESTER LIKE :requester)\n" +
            "AND (:contents IS NULL OR t.CONTENTS LIKE :contents)\n" +
            "AND (:contents_kr IS NULL OR t.CONTENTS_KR LIKE :contents_kr)\n" +
            "AND (:developer IS NULL OR t.DEVELOPER LIKE :developer)\n", nativeQuery = true)
    List<Map<String, String>> searchIssue(@Param("site") String site,
                                          @Param("module") String module,
                                          @Param("division_flag") String division_flag,
                                          @Param("applied_period_flag") String applied_period_flag,
                                          @Param("accept_flag") String accept_flag,
                                          @Param("request_confirm") String request_confirm,
                                          @Param("requester") String requester,
                                          @Param("contents") String contents,
                                          @Param("contents_kr") String contents_kr,
                                          @Param("developer") String developer);

    @Query(value = "SELECT t.CONTENT \n" +
            ",t.SITE \n" +
            ",t.DIVISION_FLAG \n" +
            ",t.APPLIED_PERIOD_FLAG \n" +
            ",t.ACCEPT_FLAG \n" +
            ",t.REQUEST_CONFIRM \n" +
            ",t.REQUESTER \n" +
            ",t.CONTENTS \n" +
            ",t.CONTENTS_KR \n" +
            ",t.DEVELOPER \n" +
            ",tme.PHOTO \n" +
            "FROM tb_m00_issue_management t \n" +
            "JOIN tb_m00_employee tme \n" +
            "ON t.DEVELOPER = tme.EMP_ID \n", nativeQuery = true)
    List<Object[]> findIssueManagementDto(String contents, String site, String modules, String division_flag,
                                          String applied_period_flag, String accept_flag, String requester_confirm,
                                          String requester, String contents_kr, String developer, Date registration_date,
                                          Date request_date);


    @Query(value = "SELECT MAX(SEQ) FROM tb_m00_issue_management ", nativeQuery = true )
    int maxSeq();

    @Query(value = "SELECT t.REQUESTER FROM tb_m00_issue_management t \n" +
            "JOIN tb_m00_employee tme \n" +
            "ON t.DEVELOPER = tme.EMP_ID \n", nativeQuery = true)
    String requester();

}
