package com.poscodx.odc.ampro015.store.jpo;

import java.lang.Iterable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.poscdx.odc.ampro015.domain.entity.QCodeItem;
import com.posco.reuse.common.errorobjects.PosBaseException;
import com.poscoict.base.share.jpo.PoscoEntityJpo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


/**
 * Auto generated class
 * 
 * 자동생성 프로그램 버전 : 1.0.0
 * 생성일시 :  2023-08-26 21:42:34.446
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:34.446;00000;홍길동;최초생성
 * 
 */
@Getter
@Setter
@Entity(name = "QCodeItem")
@Table(name = "TB_M2VD01_S_QITEM_CODE", schema = "POSM2V")
public class QCodeItemJpo
    extends PoscoEntityJpo
{
    @Id
    @Column(name = "ITEM_NUM")
    private String itemNum;
    @Column(name = "WORKS_CODE")
    private String worksCode;
    @Column(name = "DESCRIPTION1")
    private String description1;
    @Column(name = "DESCRIPTION2")
    private String description2;
    @Column(name = "MES_UNIT_OF_MEASURE")
    private String mesUnitOfMeasure;
    @Column(name = "ITEM_TYPE_GROUP_NAME")
    private String itemTypeGroupName;
    @Column(name = "LIST_PRICE_PER_UNIT")
    private BigDecimal listPricePerUnit;
    @Column(name = "CTGY_NAME")
    private String ctgyName;
    @Column(name = "PUR_MAT_ITEM_WGT")
    private BigDecimal purMatItemWgt;
    @Column(name = "ATTRIBUTE")
    private String attribute;
    @Column(name = "DESCRIPTION3")
    private String description3;
    @Column(name = "DELETE_FLAG")
    private String deleteFlag;

    /**
     * Creates a new QCodeItemJpo.
     */
    public QCodeItemJpo() {
    }

    public QCodeItemJpo(QCodeItem entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public QCodeItem toDomain() {
        QCodeItem retVal = new QCodeItem();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<QCodeItem> toDomains(Iterable<QCodeItemJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((QCodeItemJpo::toDomain)).collect(Collectors.toList());
    }

    @Override
    public void validateJpo()
        throws PosBaseException
    {
    }
}
