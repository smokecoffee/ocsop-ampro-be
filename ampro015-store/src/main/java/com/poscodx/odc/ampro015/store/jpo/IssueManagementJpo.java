package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.posco.reuse.common.errorobjects.PosBaseException;
import com.sun.org.apache.xpath.internal.operations.String;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "M00IssueManagement")
@Table(name = "TB_M00_ISSUE_MANAGEMENT", schema = "POSCTZN")
public class IssueManagementJpo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private int seq;

    @Column(name = "REGISTRATION_DATE")
    private Date registration_date;

    @NotNull
    @Column(name = "REQUESTER")
    private String requester;

    @Id
    @Column(name = "SITE")
    private String site;

    @NotNull
    @Column(name = "MODULE")
    private String module;

    @Column(name = "MENU")
    private String menu;

    @Column(name = "SCREEN_NAME")
    private String name;

    @NotNull
    @Column(name = "DIVISION_FLAG")
    private String divisionFlag;

    @NotNull
    @Column(name = "APPLIED_PERIOD_FLAG")
    private String appliedPeriodFlag;

    @NotNull
    @Column(name = "CONTENTS")
    private String contents;

    @NotNull
    @Column(name = "CONTENTS_KR")
    private String contentsKR;

    @NotNull
    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "DEVELOPER")
    private String developer;

    @Column(name = "ACCEPT_FLAG")
    private String acceptFlag;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DEVELOP_COMMENTS")
    private String developComments;

    @Column(name = "DEVELOP_FROM_DATE")
    private String developFromDate;

    @Column(name = "DEVELEOP_TO_DATE")
    private String developToDate;

    @Column(name = "CATEGORY_FLAG")
    private String categooryFlag;

    @Column(name = "REQUESTER_CONFIRM")
    private String requesterConfirm;

    @Column(name = "FINAL_CONFIRM_DATE")
    private String finalConfirmDate;

    public IssueManagementJpo(IssueManagement domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public IssueManagement toDomain() {
        IssueManagement domainEntity = new IssueManagement();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<IssueManagement> toDomains(Iterable<IssueManagementJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(IssueManagementJpo::toDomain).collect(Collectors.toList());
    }

}
