package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.posco.reuse.common.errorobjects.PosBaseException;
import com.poscoict.base.share.jpo.PoscoEntityJpo;
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
@Entity(name = "Field")
@Table(name = "TB_A01_FIELD", schema = "VIVA-ODC")
public class FieldJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "SORT")
    private int sort;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CREATE_BY")
    private int createBy;

    @Column(name = "CREATE_AT")
    private Date createAt;

    @Column(name = "UPDATE_BY")
    private int updateBy;

    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @Column(name = "DELETE_AT")
    private Date deleteAt;

    public FieldJpo(Field domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Field toDomain() {
        Field domainEntity = new Field();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Field> toDomains(Iterable<FieldJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(FieldJpo::toDomain).collect(Collectors.toList());
    }

}
