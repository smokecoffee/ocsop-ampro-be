package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.utils.Constants;
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
@Entity(name = "Image")
@Table(name = "TB_A01_IMAGE", schema = "POSCTZN")
public class ImageJpo extends PoscoEntityJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "PATH")
    private String path;

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

    public ImageJpo(Image domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Image toDomain() {
        Image domainEntity = new Image();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Image> toDomains(Iterable<ImageJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(ImageJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void validateJpo() throws PosBaseException {

    }

    @PostLoad
    public void imagePatchLoad() {
        path = Constants.UPLOAD_STATIC_ASSET_FOLDER_PATH + path;
    }
}
