package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Task")
public class Pme00TaskResource {
    private final ServiceLifecycle serviceLifecycle;
    @CrossOrigin
    @GetMapping(path = "/all")
    public List<M00Task> findAll(@RequestParam("projectNumber") String projectNumber) {
        return this.serviceLifecycle.requestTaskService().findAll(projectNumber);
    }
    @CrossOrigin
    @PostMapping(path = "/find")
    public M00Task find(@RequestBody M00TaskId m00TaskId) {
        return this.serviceLifecycle.requestTaskService().find(m00TaskId);
    }
}
