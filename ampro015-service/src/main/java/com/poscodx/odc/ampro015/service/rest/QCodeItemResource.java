package com.poscodx.odc.ampro015.service.rest;

import java.util.List;
import com.poscdx.odc.ampro015.domain.entity.QCodeItem;
import com.poscdx.odc.ampro015.domain.spec.QCodeItemService;
import com.poscoict.base.share.domain.NameValueList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RestController
@RequestMapping(path = "/qcodeitem")
public class QCodeItemResource {
    private final QCodeItemService service;

    public QCodeItemResource(QCodeItemService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<QCodeItem> findAll() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{id}")
    public QCodeItem find(
        @PathVariable("id")
        String id) {
        return this.service.find(id);
    }

    @PostMapping
    public QCodeItem register(
        @org.springframework.web.bind.annotation.RequestBody
        QCodeItem entity) {
        return this.service.register(entity);
    }

    @PutMapping(path = "/{id}")
    public QCodeItem modify(
        @PathVariable("id")
        String id,
        @org.springframework.web.bind.annotation.RequestBody
        NameValueList nameValues) {
        return this.service.modify(id, nameValues);
    }

    @DeleteMapping
    public void remove(String id) {
        this.service.remove(id);
    }
}
