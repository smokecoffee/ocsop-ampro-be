package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.spec.FieldService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class A01FieldResource {
    private final FieldService service;

    public A01FieldResource(FieldService service) {
        this.service = service;
    }
}
