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
@Entity(name = "Field")
@Table(name = "TB_A01_FIELD", schema = "AMPRO")
public class FieldJpo extends PoscoEntityJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "ORDER_BY")
    private int orderBy;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

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
