package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.spec.M00Codes030Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/codes030")
public class M00Codes030Resource {

    private final M00Codes030Service service;

    public M00Codes030Resource(M00Codes030Service service) {
        this.service = service;
    }

    @GetMapping(path="/all")
    public List<M00Codes030> findAll(){
        return this.service.findAll();
    }

    @PostMapping(path="/find")
    public M00Codes030 find(@RequestBody M00Codes030Id id){
        return this.service.find(id);
    }

    @PostMapping
    public M00Codes030 register(@RequestBody M00Codes030 entity){
        return this.service.register(entity);
    }

    @PutMapping
    public void modify(@RequestBody List<M00Codes030> entityList){
        this.service.modify(entityList);
    }

    @DeleteMapping
    public void delete(@RequestBody M00Codes030Id id){
        this.service.remove(id);
    }
}
