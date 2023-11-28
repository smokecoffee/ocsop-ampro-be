package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.spec.Pme00MeetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00Meeting")
public class Pme00MeetingResource {

    private final Pme00MeetingService service;

    public Pme00MeetingResource(Pme00MeetingService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00Meeting> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00Meeting find(@RequestBody int entityId) {
        return this.service.find(entityId);
    }

    @CrossOrigin
    @PostMapping
    public Pme00Meeting register(@RequestBody Pme00Meeting entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping(path = "/modify")
    public void modify(@RequestBody List<Pme00Meeting> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping(path = "/")
    public void remove(@RequestBody int entityId) {
        this.service.remove(entityId);
    }

}
