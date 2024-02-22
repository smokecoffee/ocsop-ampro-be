package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00RoleUser")
@Table(name = "TB_PME00_ROLE_USER", schema = "VIVA-ODC")
public class Pme00RoleUserJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "EMPLOYEE_ID")
    private String empId;
    @Column(name = "ROLE_ID")
    private int roleId;

    public Pme00RoleUserJpo(Pme00RoleUser domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00RoleUser toDomain() {
        Pme00RoleUser domainEntity = new Pme00RoleUser();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00RoleUser> toDomains(Iterable<Pme00RoleUserJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00RoleUserJpo::toDomain).collect(Collectors.toList());
    }
}
