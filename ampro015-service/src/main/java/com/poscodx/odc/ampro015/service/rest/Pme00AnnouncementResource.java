package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.spec.Pme00AnnouncementService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pme00AnnouncementResource {
    private final Pme00AnnouncementService service;

    public Pme00AnnouncementResource(Pme00AnnouncementService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public List<Pme00Announcement> getAll() {
        return this.service.findAll();
    }

    @PostMapping(path = "")
    public Pme00Announcement add(@RequestBody Pme00Announcement announcement) {
        return this.service.register(announcement);
    }

    @PutMapping(path = "")
    public void edit(@RequestBody List<Pme00Announcement> announcements) {
        this.service.modify(announcements);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        this.service.remove(id);
    }

}
