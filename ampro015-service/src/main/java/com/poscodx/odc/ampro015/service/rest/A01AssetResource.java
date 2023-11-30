package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.spec.AssetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class A01AssetResource {
    private final AssetService service;

    public A01AssetResource(AssetService service) {
        this.service = service;
    }

}
