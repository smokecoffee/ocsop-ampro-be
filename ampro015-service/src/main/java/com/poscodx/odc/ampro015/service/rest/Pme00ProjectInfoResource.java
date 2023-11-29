package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00ProjectInfo")
public class Pme00ProjectInfoResource {
    private final Pme00ProjectInfoService service;

    public Pme00ProjectInfoResource(Pme00ProjectInfoService service) { this.service = service; }

    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00ProjectInfo> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00ProjectInfo find(@RequestBody Pme00ProjectInfo entity) {
        return this.service.find(entity.getCdV());
    }

    @CrossOrigin
    @PostMapping
    public Pme00ProjectInfo register(@RequestBody Pme00ProjectInfo entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping
    public void modify(@RequestBody List<Pme00ProjectInfo> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping
    public void remove(@RequestBody Pme00ProjectInfo entity) {
        this.service.remove(entity.getCdV());
    }

}
