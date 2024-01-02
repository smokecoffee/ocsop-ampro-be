package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSettingDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class Pme00DashboardResource {
    private final ServiceLifecycle serviceLifecycle;

    public Pme00DashboardResource(ServiceLifecycle serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    @GetMapping(path = "/{id}")
    public Pme00DashboardSettingDto loadDashboard(@PathVariable("id") String empId) {
        return this.serviceLifecycle.requestLevel2DashboardService().loadDashboard(serviceLifecycle, empId);
    }

    @CrossOrigin
    @PostMapping
    public Pme00DashboardSetting register(@RequestBody Pme00DashboardSetting entity) {
        return this.serviceLifecycle.requestPme00DashboardSettingService().register(entity);
    }

    @CrossOrigin
    @PutMapping
    public void modify(@RequestBody Pme00DashboardSetting entity) {
        this.serviceLifecycle.requestPme00DashboardSettingService().modify(entity);
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id)")
    public void remove(@RequestBody int id) {
        this.serviceLifecycle.requestPme00DashboardSettingService().remove(id);
    }

}
