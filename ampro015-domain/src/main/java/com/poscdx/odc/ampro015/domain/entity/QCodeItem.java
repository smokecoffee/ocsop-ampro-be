package com.poscdx.odc.ampro015.domain.entity;

import java.math.BigDecimal;
import com.poscoict.base.share.domain.PoscoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
@NoArgsConstructor
public class QCodeItem
    extends PoscoEntity
{
    private String itemNum;
    private String worksCode;
    private String description1;
    private String description2;
    private String mesUnitOfMeasure;
    private String itemTypeGroupName;
    private BigDecimal listPricePerUnit;
    private String ctgyName;
    private BigDecimal purMatItemWgt;
    private String attribute;
    private String description3;
    private String deleteFlag;
}
