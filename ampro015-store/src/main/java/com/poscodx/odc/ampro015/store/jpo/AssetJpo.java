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
@Entity(name = "Asset")
@Table(name = "TB_A01_ASSET", schema = "AMPRO")
public class AssetJpo extends PoscoEntityJpo {

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

    @Override
    public void validateJpo() throws PosBaseException {

    }
}
