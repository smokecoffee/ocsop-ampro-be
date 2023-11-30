package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.spec.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class A01ImageResource {
    private final ImageService service;


    public A01ImageResource(ImageService service) {
        this.service = service;
    }
}
