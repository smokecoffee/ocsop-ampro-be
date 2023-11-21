package com.poscodx.odc.ampro015.store.jpo;

import com.posco.reuse.common.errorobjects.PosBaseException;
import com.poscoict.base.share.jpo.PoscoEntityJpo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Image")
@Table(name = "TB_A01_IMAGE", schema = "AMPRO")
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
    @Override
    public void validateJpo() throws PosBaseException {

    }
}
