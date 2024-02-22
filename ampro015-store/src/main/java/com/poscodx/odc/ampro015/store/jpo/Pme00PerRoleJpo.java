package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00PerRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "Pme00PermissionRole")
@Table(name = "TB_PME00_PERMISSION_ROLE", schema = "VIVA-ODC")
public class Pme00PerRoleJpo
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ROLE_ID")
    private int roleId;
    @Column(name = "PERMISSION_ID")
    private int permissionId;

    public Pme00PerRoleJpo() {
    }

    public Pme00PerRoleJpo(Pme00PerRole entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Pme00PerRole toDomain() {
        Pme00PerRole retVal = new Pme00PerRole();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<Pme00PerRole> toDomains(Iterable<com.poscodx.odc.ampro015.store.jpo.Pme00PerRoleJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((com.poscodx.odc.ampro015.store.jpo.Pme00PerRoleJpo::toDomain)).collect(Collectors.toList());
    }

//    @Override
//    public void validateJpo()
//        throws PosBaseException
//    {
//    }
}
