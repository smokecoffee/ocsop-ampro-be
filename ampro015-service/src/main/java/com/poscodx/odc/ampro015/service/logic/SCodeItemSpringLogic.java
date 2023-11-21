package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.SCodeItemLogic;
import com.poscdx.odc.ampro015.domain.store.SCodeItemStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


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
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SCodeItemSpringLogic
    extends SCodeItemLogic
{

    public SCodeItemSpringLogic(SCodeItemStore store) {
        super(store);
    }
}
