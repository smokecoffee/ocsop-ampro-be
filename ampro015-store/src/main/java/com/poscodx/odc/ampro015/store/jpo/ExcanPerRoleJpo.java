package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.ExcanPerRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "ExcanPermissionRole")
@Table(name = "TB_EXCAN_PERMISSION_ROLE", schema = "AMPRO")
public class ExcanPerRoleJpo
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PERMISSION_ID")
    private int permissionId;

    public ExcanPerRoleJpo() {
    }

    public ExcanPerRoleJpo(ExcanPerRole entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public ExcanPerRole toDomain() {
        ExcanPerRole retVal = new ExcanPerRole();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<ExcanPerRole> toDomains(Iterable<ExcanPerRoleJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((ExcanPerRoleJpo::toDomain)).collect(Collectors.toList());
    }

//    @Override
//    public void validateJpo()
//        throws PosBaseException
//    {
//    }
}
