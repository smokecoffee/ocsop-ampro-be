package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, IssueManagementId> {
    @Query(value = "select * from tb_m00_issue_management tmim where seq like '%' and site like '%'",nativeQuery = true)
    List<IssueManagementJpo> findBySeqAndSite(int seq,String site);
    @Query(value =
                    "SELECT *,emp.photo From tb_m00_issue_management AS ism " +
                    "join tb_m00_employee AS emp ON ism.developer = emp.emp_id " +
                    "where contents LIKE %:contents " +
                    "and site like %:site " +
                    "and module like %:module " +
                    "and division_flag like %:division_flag " +
                    "and applied_period_flag like %:applied_period_flag " +
                    "and accept_flag like %:accept_flag " +
                    "and requester_confirm like %:requester_confirm " +
                    "and requester like %:requester " +
                    "and contents like %:contents " +
                    "and contents_kr like %:contents_kr " +
                    "and developer like %:developer " +
                            "and ((:fromStartDate IS NULL ) OR (ism.registration_date >= :fromStartDate ))\n"+
                            "and ((:toStartDate IS NULL) OR (ism.registration_date <= :toStartDate))\n"+
                            "and ((:fromEndDate IS NULL) OR (ism.request_date >= :fromEndDate ))\n" +
                            "and ((:toEndDate IS NULL) OR (ism.request_date <= :toEndDate))\n"
            , nativeQuery = true)
    List<IssueManagementJpo> findIssueInfo( @Param("contents") String contents,
                                            @Param("site") String site,
                                            @Param("module") String modules,
                                            @Param("division_flag") String division_flag,
                                            @Param("applied_period_flag") String applied_period_flag,
                                            @Param("accept_flag") String accept_flag,
                                            @Param("requester_confirm") String requester_confirm,
                                            @Param("requester") String requester,
                                            @Param("contents_kr") String contents_kr,
                                            @Param("developer") String developer,
                                            @Param("fromStartDate") String registrationFromStartDate, @Param("toStartDate") String registrationToEndDate,
                                            @Param("fromEndDate") String requestFromStartDate, @Param("toEndDate") String requestToEndDate
                                            );

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
            "AND (:contents IS NULL OR t.CONTENTS LIKE :contents)\n"+
            "AND (:contents_kr IS NULL OR t.CONTENTS_KR LIKE :contents_kr)\n"+
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
            "ON t.DEVELOPER = tme.EMP_ID \n",nativeQuery = true)
    List<Object[]> findIssueManagementDto(String contents, String site, String modules, String division_flag,
                                          String applied_period_flag, String accept_flag, String requester_confirm,
                                          String requester, String contents_kr, String developer, Date registration_date,
                                          String request_date);

}
