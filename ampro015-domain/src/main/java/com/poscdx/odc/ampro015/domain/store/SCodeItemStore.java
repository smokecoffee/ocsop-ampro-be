package com.poscdx.odc.ampro015.domain.store;

import java.math.BigDecimal;
import java.util.List;

import com.poscdx.odc.ampro015.domain.entity.SCodeItem;


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
public interface SCodeItemStore {

    public SCodeItem retrieve(String itemNum, BigDecimal poLineNum, String poNum);

    public List<SCodeItem> retrieveAll();

    public SCodeItem update(SCodeItem entity);

    public SCodeItem create(SCodeItem entity);

    public void delete(String itemNum, BigDecimal poLineNum, String poNum);
}
