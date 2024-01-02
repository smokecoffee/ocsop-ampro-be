package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00Role")
@Table(name = "TB_PME00_ROLE", schema = "POSCTZN")
public class Pme00RoleJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private int status;

    public Pme00RoleJpo(Pme00Role domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00Role toDomain() {
        Pme00Role domainEntity = new Pme00Role();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00Role> toDomains(Iterable<Pme00RoleJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00RoleJpo::toDomain).collect(Collectors.toList());
    }
}
