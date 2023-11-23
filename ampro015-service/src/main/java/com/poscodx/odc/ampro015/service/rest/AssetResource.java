package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
public class AssetResource {
    private final AssetService service;

    public AssetResource(AssetService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping(path = "/find/{id}")
    public Asset find(@PathVariable String id) {
        return this.service.retrieve(id);
    }

}
