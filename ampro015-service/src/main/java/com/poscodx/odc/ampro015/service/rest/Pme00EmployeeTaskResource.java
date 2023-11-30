package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00EmployeeTask")
public class Pme00EmployeeTaskResource {
    private final Pme00EmployeeTaskService service;

    public Pme00EmployeeTaskResource(Pme00EmployeeTaskService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00EmployeeTask> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00EmployeeTask find(@RequestBody M00TaskId m00TaskId) {
        return this.service.find(m00TaskId);
    }

    @CrossOrigin
    @PostMapping
    public Pme00EmployeeTask register(@RequestBody Pme00EmployeeTask entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping
    public void modify(@RequestBody List<Pme00EmployeeTask> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping
    public void remove(@RequestBody M00TaskId m00TaskId) {
        this.service.remove(m00TaskId);
    }

}
