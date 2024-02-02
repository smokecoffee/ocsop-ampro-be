package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSettingDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pme00DashboardResource {
    private final ServiceLifecycle serviceLifecycle;

    public Pme00DashboardResource(ServiceLifecycle serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    @GetMapping(path = "/{id}")
    public Pme00DashboardSettingDto loadDashboard(@PathVariable("id") String empId) {
        return this.serviceLifecycle.requestLevel3DashboardService().loadDashboard(serviceLifecycle, empId);
    }

    @PostMapping
    public Pme00DashboardSettingDto modifyDashboard(@RequestBody Pme00DashboardSetting entity) {
        return this.serviceLifecycle.requestLevel3DashboardService().modifyDashboard(serviceLifecycle, entity);
    }


}
