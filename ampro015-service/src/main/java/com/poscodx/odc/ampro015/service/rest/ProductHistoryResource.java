/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-service
 * Source File Name   : com.posco.mes3.sp.rest.ProductHistoryResource
 * Description        :
 * Author             : poscouser
 * Version            : 1.0.0
 * File Name related  :
 * Class Name related :
 * Created Date       : ${date}
 * Updated Date       : ${date}
 * Last modifier      : poscouser
 * Updated content    : 최초작성
 *
 *==================================================================================*/
package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.ProductHistory;
import com.poscoict.base.share.domain.NameValueList;
import org.springframework.web.bind.annotation.*;

import com.poscdx.odc.ampro015.domain.spec.ProductHistoryService;

import java.util.List;

/**
 * ProductHistoryResource.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@RestController
@RequestMapping("/producthistory")
public class ProductHistoryResource {

    private final ProductHistoryService service;

    public ProductHistoryResource(ProductHistoryService service) {
        this.service = service;
    }

    @PostMapping({"", "/"})
    public String register(@RequestBody ProductHistory entity) {
        ProductHistory savedEntity = this.service.register(entity);
        return savedEntity.getId();
    }

    @GetMapping("/all")
    public List<ProductHistory> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ProductHistory find(@PathVariable String id) {
        return this.service.find(id);
    }

    @PutMapping("/{id}")
    public void modify(@PathVariable String id, @RequestBody NameValueList nameValue) {
        this.service.modify(id, nameValue);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        this.service.remove(id);
    }
}