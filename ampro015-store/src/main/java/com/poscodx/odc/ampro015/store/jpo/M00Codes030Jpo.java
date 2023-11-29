package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
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
@Entity(name="M00Codes030")
@Table(name="TB_M00_CODES030", schema = "POSCTZN")
@IdClass(M00Codes030Id.class)
public class M00Codes030Jpo implements Serializable {

    @Id
    @Column(name = "CD_TP_ID")
    private int cdTpId;

    @Id
    @Column(name = "CATEGORY_GROUP_ID")
    private int categoryGroupId;

    @Id
    @Column(name = "CD_V")
    private String cdV;

    @Column(name = "CD_V_MEANING")
    private String cdvMeaning;

    @Column(name = "CD_V_EXPLAIN")
    private String cdVExplain;

    @Column(name = "CD_V_INQUIRY_SEQ")
    private int cdVInquirySeq;

    @Column(name = "CREATED_PROGRAM_ID")
    private String createdProgramId;

    @Column(name = "CREATION_TIMESTAMP")
    private Date creationTimestamp;

    @Column(name = "LAST_UPDATE_PROGRAM_ID")
    private String lastUpdateProgramId;

    @Column(name = "LAST_UPDATE_TIMESTAMP")
    private Date lastUpdateTimestamp;


    public M00Codes030Jpo(M00Codes030 domainEntity){
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M00Codes030 toDomain(){
        M00Codes030 domainEntity = new M00Codes030();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M00Codes030> toDomains(Iterable<M00Codes030Jpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M00Codes030Jpo::toDomain).collect(Collectors.toList());
    }

}
