package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;
import com.poscdx.odc.ampro015.domain.spec.M00Codes020Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codes020")
public class M00Codes020Resource {

    private final M00Codes020Service service;

    public M00Codes020Resource(M00Codes020Service service) {
        this.service = service;
    }

    @GetMapping(path="/all")
    public List<M00Codes020> findAll(){
        return this.service.findAll();
    }

    @PostMapping(path="/find")
    public M00Codes020 find(@RequestBody M00Codes020 entity){
        return this.service.find(entity.getCdTpId());
    }

}
