package com.poscdx.odc.ampro015.domain.logic;

import java.math.BigDecimal;
import java.util.List;

import com.poscdx.odc.ampro015.domain.entity.SCodeItem;
import com.poscdx.odc.ampro015.domain.spec.SCodeItemService;
import com.poscdx.odc.ampro015.domain.store.SCodeItemStore;
import com.poscoict.base.share.domain.NameValueList;


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
public class SCodeItemLogic
    implements SCodeItemService
{
    private final SCodeItemStore store;

    public SCodeItemLogic(SCodeItemStore store) {
        this.store = store;
    }

    @Override
    public SCodeItem find(String itemNum, BigDecimal poLineNum, String poNum) {
        return this.store.retrieve(itemNum, poLineNum, poNum);
    }

    @Override
    public List<SCodeItem> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public SCodeItem register(SCodeItem entity) {
        return this.store.create(entity);
    }

    @Override
    public SCodeItem modify(String itemNum, BigDecimal poLineNum, String poNum, NameValueList nameValues) {
        SCodeItem entity = this.store.retrieve(itemNum, poLineNum, poNum);
        entity.setValues(nameValues);
        return this.store.update(entity);
    }

    @Override
    public void remove(String itemNum, BigDecimal poLineNum, String poNum) {
        this.store.delete(itemNum, poLineNum, poNum);
    }
}
