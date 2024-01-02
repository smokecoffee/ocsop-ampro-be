package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "Pme00Permission")
@Table(name = "TB_PME00_PERMISSION", schema = "ampro015")
public class Pme00PermissionJpo
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    public Pme00PermissionJpo() {
    }

    public Pme00PermissionJpo(Pme00Permission entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Pme00Permission toDomain() {
        Pme00Permission retVal = new Pme00Permission();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<Pme00Permission> toDomains(Iterable<com.poscodx.odc.ampro015.store.jpo.Pme00PermissionJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((com.poscodx.odc.ampro015.store.jpo.Pme00PermissionJpo::toDomain)).collect(Collectors.toList());
    }

//    @Override
//    public void validateJpo()
//        throws PosBaseException
//    {
//    }
}
