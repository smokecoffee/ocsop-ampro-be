package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscodx.odc.ampro015.store.converter.image.StringCryptoConverter;
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
@Entity(name = "IssueManagement")
@Table(name = "TB_M00_ISSUE_MANAGEMENT", schema = "POSCTZN")
@IdClass(IssueManagementId.class)
public class IssueManagementJpo implements Serializable {

    @Id
    @Column(name = "SEQ")
    private int seq;

    @Id
    @Column(name = "SITE")
    private String site;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "REQUEST_DATE")
    private String requestDate;

    @NotNull
    @Column(name = "REQUESTER")
    private String requester;

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
    @Convert(converter = StringCryptoConverter.class)
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

    @Column(name = "DEVELOP_TO_DATE")
    private String developToDate;

    @Column(name = "CATEGORY_FLAG")
    private String categoryFlag;

    @Column(name = "REQUESTER_CONFIRM")
    private String requesterConfirm;

    @Column(name = "FINAL_CONFIRM_DATE")
    private String finalConfirmDate;

    @Column(name = "REQUESTER_ID")
    private String requesterId;

//    @Transient
//    private String koreaPmName;
//
//    @Transient
//    private String vietnamPlName;

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
