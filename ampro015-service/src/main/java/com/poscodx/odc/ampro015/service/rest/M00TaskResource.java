package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.spec.M00TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/M00Task")
public class M00TaskResource {
    private final M00TaskService service;

    public M00TaskResource(M00TaskService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<M00Task> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public M00Task find(@RequestBody M00TaskId id) {
        return this.service.find(id);
    }

    @CrossOrigin
    @PostMapping
    public M00Task register(@RequestBody M00Task entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping(path = "/modify")
    public void modify(@RequestBody List<M00Task> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping(path = "/")
    public void remove(@RequestBody M00TaskId id) {
        this.service.remove(id);
    }

}
