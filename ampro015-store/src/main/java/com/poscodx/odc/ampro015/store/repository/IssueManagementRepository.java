package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, IssueManagementId> {
    @Query(value = "SELECT * FROM TB_M00_ISSUE_MANAGEMENT  WHERE SEQ = :seq AND SITE LIKE :site", nativeQuery = true)
    List<IssueManagementJpo> findBySeqAndSite(@Param("seq") int seq, @Param("site") String site);


    @Query(value =
            "SELECT ISSUE.*\n" +
                    ", EMP2.NAME AS DEV_NAME\n" +
                    ", EMP1.PHOTO AS PHOTO\n" +
                    "FROM \n" +
                    "TB_M00_ISSUE_MANAGEMENT AS ISSUE\n" +
                    "JOIN TB_M00_EMPLOYEE EMP1\n" +
                    "ON EMP1.EMP_ID = ISSUE.REQUESTER_ID \n" +
                    "JOIN TB_M00_EMPLOYEE EMP2\n" +
                    "ON EMP2.EMP_ID = ISSUE.DEVELOPER \n" +
                    "WHERE 1 = 1 \n" +
                    "AND (:content IS NULL OR (ISSUE.CONTENTS LIKE CONCAT('%', :content, '%')))\n" +
                    "AND (:site IS NULL OR (ISSUE.SITE LIKE CONCAT('%', :site, '%')))\n" +
                    "AND (:module_check = true OR ISSUE.MODULE IN (:module))\n" +
                    "AND (:division_flag_check = true OR ISSUE.DIVISION_FLAG IN (:division_flag))\n" +
                    "AND (:applied_period_flag IS NULL OR (ISSUE.APPLIED_PERIOD_FLAG LIKE CONCAT('%', :applied_period_flag, '%')))\n" +
                    "AND (:accept_flag IS NULL OR (ISSUE.ACCEPT_FLAG LIKE CONCAT('%', :accept_flag, '%')))\n" +
                    "AND (:requester_confirm IS NULL OR (ISSUE.REQUESTER_CONFIRM LIKE CONCAT('%', :requester_confirm, '%')))\n" +
                    "AND (:requester IS NULL OR (ISSUE.REQUESTER = :requester))\n" +
                    "AND (:requester_id IS NULL OR (ISSUE.REQUESTER_ID = :requester_id))\n" +
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND (:fromRegistrationStartDate IS NULL OR :toRegistrationEndDate IS NULL OR (CAST(ISSUE.REGISTRATION_DATE as DATE) >=  :fromRegistrationStartDate AND CAST(ISSUE.REGISTRATION_DATE as DATE) <= :toRegistrationEndDate)) \n" +
                    "AND (:fromRequestStartDate IS NULL OR :toRequestEndDate IS NULL OR (CAST(ISSUE.REQUEST_DATE_NEW as DATE) >=  :fromRequestStartDate AND CAST(ISSUE.REQUEST_DATE_NEW as DATE) <= :toRequestEndDate))"
            , nativeQuery = true)
    List<Object[]> search(
            @Param("content") String content,
            @Param("site") List<String> site,
            @Param("module") List<String> module,
            @Param("module_check") boolean module_check,
            @Param("division_flag_check") boolean division_check,
            @Param("division_flag") List<String> division_flag,
            @Param("applied_period_flag") String applied_period_flag,
            @Param("accept_flag") String accept_flag,
            @Param("requester_confirm") String requester_confirm,
            @Param("requester") String requester,
            @Param("requester_id") String requester_id,
            @Param("contents_kr") String contents_kr,
            @Param("developer") String developer,
            @Param("fromRegistrationStartDate") String registrationFromStartDate, @Param("toRegistrationEndDate") String registrationToEndDate,
            @Param("fromRequestStartDate") String requestFromStartDate, @Param("toRequestEndDate") String requestToEndDate,
            Pageable pageable
    );

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
                    "AND (:content IS NULL OR (ISSUE.CONTENTS LIKE CONCAT('%', :content, '%')))\n" +
                    "AND (:site IS NULL OR (ISSUE.SITE LIKE CONCAT('%', :site, '%')))\n" +
                    "AND (:module_check = true OR ISSUE.MODULE IN (:module))\n" +
                    "AND (:division_flag_check = true OR ISSUE.DIVISION_FLAG IN (:division_flag))\n" +
                    "AND (:applied_period_flag IS NULL OR (ISSUE.APPLIED_PERIOD_FLAG LIKE CONCAT('%', :applied_period_flag, '%')))\n" +
                    "AND (:accept_flag IS NULL OR (ISSUE.ACCEPT_FLAG LIKE CONCAT('%', :accept_flag, '%')))\n" +
                    "AND (:requester_confirm IS NULL OR (ISSUE.REQUESTER_CONFIRM LIKE CONCAT('%', :requester_confirm, '%')))\n" +
                    "AND (:requester IS NULL OR (ISSUE.REQUESTER = :requester))\n" +
                    "AND (:requester_id IS NULL OR (ISSUE.REQUESTER_ID = :requester_id))\n" +
                    "AND (:contents_kr IS NULL OR (ISSUE.CONTENTS_KR LIKE CONCAT('%', :contents_kr, '%')))\n" +
                    "AND (:developer IS NULL OR (ISSUE.DEVELOPER LIKE CONCAT('%', :developer, '%')))\n" +
                    "AND (:fromRegistrationStartDate IS NULL OR :toRegistrationEndDate IS NULL OR (CAST(ISSUE.REGISTRATION_DATE as DATE) >=  :fromRegistrationStartDate AND CAST(ISSUE.REGISTRATION_DATE as DATE) <= :toRegistrationEndDate)) \n" +
                    "AND (:fromRequestStartDate IS NULL OR :toRequestEndDate IS NULL OR (CAST(ISSUE.REQUEST_DATE_NEW as DATE) >=  :fromRequestStartDate AND CAST(ISSUE.REQUEST_DATE_NEW as DATE) <= :toRequestEndDate))"            , nativeQuery = true)
    int countSearch(
            @Param("content") String content,
            @Param("site") List<String> site,
            @Param("module") List<String> module,
            @Param("module_check") boolean module_check,
            @Param("division_flag_check") boolean division_check,
            @Param("division_flag") List<String> division_flag,
            @Param("applied_period_flag") String applied_period_flag,
            @Param("accept_flag") String accept_flag,
            @Param("requester_confirm") String requester_confirm,
            @Param("requester") String requester,
            @Param("requester_id") String requester_id,
            @Param("contents_kr") String contents_kr,
            @Param("developer") String developer,
            @Param("fromRegistrationStartDate") String registrationFromStartDate, @Param("toRegistrationEndDate") String registrationToEndDate,
            @Param("fromRequestStartDate") String requestFromStartDate, @Param("toRequestEndDate") String requestToEndDate);

    @Query(value = "SELECT MAX(SEQ) FROM tb_m00_issue_management WHERE SITE = :site", nativeQuery = true)
    int maxSeq(@Param("site") String site);
}
