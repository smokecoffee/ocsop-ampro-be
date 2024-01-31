package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Asset;
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
@Entity(name = "Asset")
@Table(name = "TB_A01_ASSET", schema = "POSCTZN")
public class AssetJpo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "DURATION")
    private int duration;

    @Column(name = "QRCODE")
    private String qrcode;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_AT")
    private Date createAt;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @Column(name = "DELETE_AT")
    private Date deleteAt;

    public AssetJpo(Asset domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Asset toDomain() {
        Asset domainEntity = new Asset();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Asset> toDomains(Iterable<AssetJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(AssetJpo::toDomain).collect(Collectors.toList());
    }

//    @Override
//    public void validateJpo() throws PosBaseException {
//
//    }
}
