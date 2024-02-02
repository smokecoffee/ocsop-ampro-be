package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="M00Codes020")
@Table(name="TB_M00_CODES020", schema = "VIVA-ODC")
public class M00Codes020Jpo implements Serializable {

    @Id
    @Column(name = "CD_TP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cdTpId;

    @Column(name = "CD_TP")
    private String cdTp;

    @Column(name = "CREATED_PROGRAM_ID")
    private String createdProgramId;

    @Column(name = "CREATION_TIMESTAMP")
    private Date creationTimestamp;

    @Column(name = "LAST_UPDATE_PROGRAM_ID")
    private String lastUpdateProgramId;

    @Column(name = "LAST_UPDATE_TIMESTAMP")
    private Date lastUpdateTimestamp;

    @Column(name = "START_ACTIVE_DATE")
    private Date startActiveDate;

    @Column(name = "END_ACTIVE_DATE")
    private Date endActiveDate;

    @Column(name = "USE_TP")
    private String useTp;

    @Column(name = "CD_TP_VERSION")
    private String cdTpVersion;

    @Column(name = "VERSION_UP_CAUSE_DESC")
    private String versionUpCauseDesc;

    @Column(name = "CD_TP_MEANING")
    private String cdTpMeaning;

    @Column(name = "CD_TP_EXPLAIN")
    private String cdTpExplain;

    @Column(name = "MDL_REG_REQ_EMP_ID")
    private String mdlRegReqEmpId;

    @Column(name = "FK_CD_TP_ID")
    private int fkCdTpId;


    public M00Codes020Jpo(M00Codes020 domainEntity){
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M00Codes020 toDomain(){
        M00Codes020 domainEntity = new M00Codes020();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M00Codes020> toDomains(Iterable<M00Codes020Jpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M00Codes020Jpo::toDomain).collect(Collectors.toList());
    }

}
