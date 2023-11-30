package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.spec.Pme00AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00Announcement")
public class Pme00AnnouncementResource {
    private final Pme00AnnouncementService service;

    public Pme00AnnouncementResource(Pme00AnnouncementService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00Announcement> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00Announcement find(@RequestBody Pme00Announcement entity) {
        return this.service.find(entity.getId());
    }

    @CrossOrigin
    @PostMapping
    public Pme00Announcement register(@RequestBody Pme00Announcement entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping
    public void modify(@RequestBody List<Pme00Announcement> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping
    public void remove(@RequestBody Pme00Announcement entity) {
        this.service.remove(entity.getId());
    }

}
