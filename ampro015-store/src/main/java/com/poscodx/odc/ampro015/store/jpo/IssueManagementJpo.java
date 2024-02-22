package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "IssueManagement")
@Table(name = "TB_M00_ISSUE_MANAGEMENT", schema = "VIVA-ODC")
@IdClass(IssueManagementId.class)
public class IssueManagementJpo implements Serializable {

    @Id
    @Column(name = "SEQ")
    private int seq;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "REQUEST_DATE_NEW")
    private Date requestDate;

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
    private String screenName;

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
//    @Convert(converter = StringCryptoConverter.class)
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

    @Column(name = "CATEGORY_FLAG")
    private String categoryFlag;

    @Column(name = "REQUESTER_CONFIRM")
    private String requesterConfirm;

    @Column(name = "REQUESTER_ID")
    private String requesterId;

    @Column(name = "DEVELOP_FROM_DATE_NEW")
    private Date developFromDate;

    @Column(name = "DEVELOP_TO_DATE_NEW")
    private Date developToDate;

    @Column(name = "FINAL_CONFIRM_DATE_NEW")
    private Date finalConfirmDate;


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