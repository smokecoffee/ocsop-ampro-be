package com.poscodx.odc.ampro015.service.rest;

import java.math.BigDecimal;
import java.util.List;
import com.poscdx.odc.ampro015.domain.entity.SCodeItem;
import com.poscdx.odc.ampro015.domain.spec.SCodeItemService;
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
 * 생성일시 :  2023-08-26 21:42:35.32
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:35.32;00000;홍길동;최초생성
 * 
 */
@RestController
@RequestMapping(path = "/scodeitem")
public class SCodeItemResource {
    private final SCodeItemService service;

    public SCodeItemResource(SCodeItemService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<SCodeItem> findAll() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{itemNum}/{poLineNum}/{poNum}")
    public SCodeItem find(
        @PathVariable("itemNum")
        String itemNum,
        @PathVariable("poLineNum")
        BigDecimal poLineNum,
        @PathVariable("poNum")
        String poNum) {
        return this.service.find(itemNum, poLineNum, poNum);
    }

    @PostMapping
    public SCodeItem register(
        @org.springframework.web.bind.annotation.RequestBody
        SCodeItem entity) {
        return this.service.register(entity);
    }

    @PutMapping(path = "/{itemNum}/{poLineNum}/{poNum}")
    public SCodeItem modify(
        @PathVariable("itemNum")
        String itemNum,
        @PathVariable("poLineNum")
        BigDecimal poLineNum,
        @PathVariable("poNum")
        String poNum,
        @org.springframework.web.bind.annotation.RequestBody
        NameValueList nameValues) {
        return this.service.modify(itemNum, poLineNum, poNum, nameValues);
    }

    @DeleteMapping(path = "/{itemNum}/{poLineNum}/{poNum}")
    public void remove(
        @PathVariable("itemNum")
        String itemNum,
        @PathVariable("poLineNum")
        BigDecimal poLineNum,
        @PathVariable("poNum")
        String poNum) {
        this.service.remove(itemNum, poLineNum, poNum);
    }
}
