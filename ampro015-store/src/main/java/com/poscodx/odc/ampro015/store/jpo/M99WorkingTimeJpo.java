package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M99WorkingTime;
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
@Entity(name="M99WorkingTime")
@Table(name="TB_M00_WORKING_TIME", schema = "VIVA-ODC")
public class M99WorkingTimeJpo implements Serializable {

    @Column(name = "EMP_ID")
    private String empId;
    @Column(name = "EQP_SAT_DT")
    private String eqpSatDt;
    @Column(name = "REMARK1")
    private String remark1;
    @Column(name = "REMARK2")
    private String remark2;
    @Column(name = "REMARK3")
    private String remark3;
    @Column(name = "REMARK4")
    private String remark4;

    public M99WorkingTimeJpo(M99WorkingTime domainEntity){
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M99WorkingTime toDomain(){
        M99WorkingTime domainEntity = new M99WorkingTime();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M99WorkingTime> toDomains(Iterable<M99WorkingTimeJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M99WorkingTimeJpo::toDomain).collect(Collectors.toList());
    }

}
