package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00WorkingTime;
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
@Entity(name="M99WorkingTime")
@Table(name="TB_PME00_WORKING_TIME", schema = "VIVA-ODC")
public class Pme00WorkingTimeJpo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private int seq;
    @Column(name = "EMP_ID")
    private String empId;
    @Column(name = "REGISTER_DATE")
    private Date registerDate;
    @Column(name = "ARRIVE")
    private Date arrive;
    @Column(name = "LEAVE")
    private Date leave;
    @Column(name = "REMARK1")
    private String remark1;
    @Column(name = "REMARK2")
    private String remark2;
    @Column(name = "REMARK3")
    private String remark3;
    @Column(name = "REMARK4")
    private String remark4;

    public Pme00WorkingTimeJpo(Pme00WorkingTime domainEntity){
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00WorkingTime toDomain(){
        Pme00WorkingTime domainEntity = new Pme00WorkingTime();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00WorkingTime> toDomains(Iterable<Pme00WorkingTimeJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00WorkingTimeJpo::toDomain).collect(Collectors.toList());
    }

}
