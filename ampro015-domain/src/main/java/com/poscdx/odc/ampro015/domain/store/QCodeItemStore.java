package com.poscdx.odc.ampro015.domain.store;

import java.util.List;

import com.poscdx.odc.ampro015.domain.entity.QCodeItem;


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
public interface QCodeItemStore {

    public QCodeItem retrieve(String id);

    public List<QCodeItem> retrieveAll();

    public QCodeItem update(QCodeItem entity);

    public QCodeItem create(QCodeItem entity);

    public void delete(String id);
}
