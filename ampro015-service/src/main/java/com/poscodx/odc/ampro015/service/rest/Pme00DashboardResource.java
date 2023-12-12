package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00Dashboard;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class Pme00DashboardResource {
    private final ServiceLifecycle serviceLifecycle;

    public Pme00DashboardResource(ServiceLifecycle serviceLifecycle) {
        this.serviceLifecycle = serviceLifecycle;
    }

    @GetMapping(path = "")
    public Pme00Dashboard loadDashboard() {
        return this.serviceLifecycle.requestLevel2DashboardService().loadDashboard(serviceLifecycle);
    }
}
