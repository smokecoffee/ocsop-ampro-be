package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import com.poscdx.odc.ampro015.domain.spec.Pme00MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pme00Member")
public class Pme00MemberResource {
    private final Pme00MemberService service;

    public Pme00MemberResource(Pme00MemberService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<Pme00Member> findAll() {
        return this.service.findAll();
    }

    @CrossOrigin
    @PostMapping(path = "/find")
    public Pme00Member find(@RequestBody int id) {
        return this.service.find(id);
    }

    @CrossOrigin
    @PostMapping
    public Pme00Member register(@RequestBody Pme00Member entity) {
        return this.service.register(entity);
    }

    @CrossOrigin
    @PutMapping(path = "/modify")
    public void modify(@RequestBody List<Pme00Member> entityList) {
        this.service.modify(entityList);
    }

    @CrossOrigin
    @DeleteMapping(path = "/")
    public void remove(@RequestBody int id) {
        this.service.remove(id);
    }

}
