package com.poscdx.odc.ampro015.domain.logic;

import java.util.List;

import com.poscdx.odc.ampro015.domain.entity.QCodeItem;
import com.poscdx.odc.ampro015.domain.spec.QCodeItemService;
import com.poscdx.odc.ampro015.domain.store.QCodeItemStore;
import com.poscoict.base.share.domain.NameValueList;


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
public class QCodeItemLogic
    implements QCodeItemService
{
    private final QCodeItemStore store;

    public QCodeItemLogic(QCodeItemStore store) {
        this.store = store;
    }

    @Override
    public QCodeItem find(String id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<QCodeItem> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public QCodeItem register(QCodeItem entity) {
        return this.store.create(entity);
    }

    @Override
    public QCodeItem modify(String id, NameValueList nameValues) {
        QCodeItem entity = this.store.retrieve(id);
        entity.setValues(nameValues);
        return this.store.update(entity);
    }

    @Override
    public void remove(String id) {
        this.store.delete(id);
    }
}
