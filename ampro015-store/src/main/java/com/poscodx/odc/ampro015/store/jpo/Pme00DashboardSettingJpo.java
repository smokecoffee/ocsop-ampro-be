package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00DashboardSetting")
@Table(name = "TB_PME00_SETTING", schema = "VIVA-ODC")
public class Pme00DashboardSettingJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "SETTING")
    private String setting;

    public Pme00DashboardSettingJpo(Pme00DashboardSetting domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00DashboardSetting toDomain() {
        Pme00DashboardSetting domainEntity = new Pme00DashboardSetting();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00DashboardSetting> toDomains(Iterable<Pme00DashboardSettingJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00DashboardSettingJpo::toDomain).collect(Collectors.toList());
    }
}
