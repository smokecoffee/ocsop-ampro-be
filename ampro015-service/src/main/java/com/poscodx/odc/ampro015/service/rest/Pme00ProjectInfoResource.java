package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pme00ProjectInfo")
public class Pme00ProjectInfoResource {
    private final Pme00ProjectInfoService service;

    public Pme00ProjectInfoResource(Pme00ProjectInfoService service) { this.service = service; }
}
