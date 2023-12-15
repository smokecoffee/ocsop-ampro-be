package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.posco.reuse.common.errorobjects.PosBaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00ProjectInfo")
@Table(name = "tb_pme00_project_info", schema = "POSCTZN")
public class Pme00ProjectInfoJpo {
    @Id
    @Column(name = "CD_V")
    private String cdV;

    @Column(name = "PERIOD")
    private int period;

    @Column(name = "KOREA_PM")
    private String koreaPm;

    @Column(name = "VIETNAM_PL")
    private String vietnamPl;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "FRAMEWORK")
    private String framework;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IMAGE")
    private String image;


    @Transient
    private String koreaPmName;

    @Transient
    private String vietnamPlName;

    public Pme00ProjectInfoJpo(Pme00ProjectInfo domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00ProjectInfo toDomain() {
        Pme00ProjectInfo domainEntity = new Pme00ProjectInfo();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00ProjectInfo> toDomains(Iterable<Pme00ProjectInfoJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00ProjectInfoJpo::toDomain).collect(Collectors.toList());
    }

}
