package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00WorkingTime;
import com.poscdx.odc.ampro015.domain.entity.S91Menu;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    private final ServiceLifecycle serviceLifecycle;

    @GetMapping("/render-qrcode")
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }


    @GetMapping(path = "/getActiveEmp")
    public List<M00Employee> getActiveEmployee() {
        return this.serviceLifecycle.requestM00EmployeeService().getActiveEmployee();
    }

    @PostMapping(path = "/upload/{service}")
    public String uploadFile(@PathVariable("service") String serviceName,
                             @RequestParam ("file") MultipartFile image) {
        return this.serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, serviceName, image);
    }

    @PostMapping(path = "/file-delete/{service}")
    public boolean deleteFile(@PathVariable("service") String serviceName,
                             @RequestBody List<String> filenameList) {
        return this.serviceLifecycle.requestLevel2Service().removeFile(Utils.UPLOAD_BUCKET, serviceName, filenameList);
    }

    @GetMapping(path = "/getTaskStatus")
    public List<TaskStatusDto> getTaskStatus() {
        return this.serviceLifecycle.requestM00Codes030Service().getTaskStatus();
    }

    @PostMapping(path = "/left-menu")
    public List<S91Menu> getLeftMenuByPermission(@RequestBody List<String> permissionList) {
        return serviceLifecycle.requestS91MenuService().findMenuByPermission(permissionList);
    }

    @GetMapping(path = "/test")
    public List<Pme00WorkingTime> test(@RequestBody String recipient) {
        return this.serviceLifecycle.requestPme00WorkingTimeService().findAll();
    }
}
