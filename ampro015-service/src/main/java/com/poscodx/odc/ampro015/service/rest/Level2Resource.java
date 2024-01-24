package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00Password;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    @Value("${minio.bucketName}")
    private String bucketName;

    private final ServiceLifecycle serviceLifecycle;


    @GetMapping("/render-qrcode")
    @CrossOrigin
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }


    @GetMapping(path = "/getActiveEmp")
    @CrossOrigin
    public List<M00Employee> getActiveEmployee() {
        return this.serviceLifecycle.requestM00EmployeeService().getActiveEmployee();
    }

    @CrossOrigin
    @PostMapping(path = "/upload/{service}")
    public String uploadFile(@PathVariable("service") String serviceName,
                             @RequestParam ("file") MultipartFile image) {
        return this.serviceLifecycle.requestLevel2Service().uploadFile(bucketName, serviceName, image);
    }

    @CrossOrigin
    @PostMapping(path = "/file-delete/{service}")
    public String deleteFile(@PathVariable("service") String serviceName,
                             @RequestBody List<String> filenameList) {
        return this.serviceLifecycle.requestLevel2Service().removeFile(bucketName, serviceName, filenameList);
    }

    @CrossOrigin
    @GetMapping(path = "/getTaskStatus")
    public List<TaskStatusDto> getTaskStatus() {
        return this.serviceLifecycle.requestM00Codes030Service().getTaskStatus();
    }

    @CrossOrigin
    @GetMapping(path = "/test")
    public List<Pme00Password> test() {
        return this.serviceLifecycle.requestPasswordService().findAll();
    }
}
