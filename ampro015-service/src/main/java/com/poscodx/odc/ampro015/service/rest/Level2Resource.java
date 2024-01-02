package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    private final ServiceLifecycle serviceLifecycle;


    @GetMapping("/render-qrcode")
    @CrossOrigin
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }


    @GetMapping(path = "/getActiveEmp")
    @CrossOrigin
    public List<EmployeeDto> getActiveEmployee() {
        return this.serviceLifecycle.requestPme00ProjectInfoService().getActiveEmployee();
    }

    @CrossOrigin
    @PostMapping(path = "/upload/{service}")
    public String uploadFile(@PathVariable("service") String serviceName,
                             @RequestParam ("file") MultipartFile image) {
        return this.serviceLifecycle.requestLevel2Service().uploadFile(serviceName, image);
    }
}
