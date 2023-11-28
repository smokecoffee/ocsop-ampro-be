package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeMeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00EmployeeMeeting")
public class Pme00EmployeeMeetingResource {
    private final Pme00EmployeeMeetingService service;

    public Pme00EmployeeMeetingResource(Pme00EmployeeMeetingService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00EmployeeMeeting> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00EmployeeMeeting find(@RequestBody int id) {
        return this.service.find(id);
    }

    @CrossOrigin
    @PostMapping
    public Pme00EmployeeMeeting register(@RequestBody Pme00EmployeeMeeting entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping(path = "/modify")
    public void modify(@RequestBody List<Pme00EmployeeMeeting> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping(path = "/")
    public void remove(@RequestBody int id) {
        this.service.remove(id);
    }

}
