package com.poscodx.odc.ampro015.store.jpo;

import com.posco.reuse.common.errorobjects.PosBaseException;
import com.poscoict.base.share.jpo.PoscoEntityJpo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @Override
    public void validateJpo() throws PosBaseException {

    }
}
