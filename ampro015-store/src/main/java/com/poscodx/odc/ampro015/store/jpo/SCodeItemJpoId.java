package com.poscodx.odc.ampro015.store.jpo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;


/**
 * Auto generated class
 * 
 * 자동생성 프로그램 버전 : 1.0.0
 * 생성일시 :  2023-08-26 21:42:35.32
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:35.32;00000;홍길동;최초생성
 * 
 */
@Data
public class SCodeItemJpoId
    implements Serializable
{
    private String itemNum;
    private BigDecimal poLineNum;
    private String poNum;
}
