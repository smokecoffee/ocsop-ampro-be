package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    private final ServiceLifecycle serviceLifecycle;

    @CrossOrigin
    @GetMapping("/render-qrcode")
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }

    @CrossOrigin
    @GetMapping(path = "/getActiveEmp")
    public List<EmployeeDto> getActiveEmployee() {
        return this.serviceLifecycle.requestPme00ProjectInfoService().getActiveEmployee();
    }
}
