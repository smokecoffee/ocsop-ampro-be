package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
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
    public List<M00Employee> getActiveEmployee() {
        return this.serviceLifecycle.requestM00EmployeeService().getActiveEmployee();
    }

    @CrossOrigin
    @PostMapping(path = "/upload/{service}")
    public String uploadFile(@PathVariable("service") String serviceName,
                             @RequestParam ("file") MultipartFile image) {
        return this.serviceLifecycle.requestLevel2Service().uploadFile(ConstantUtil.UPLOAD_BUCKET, serviceName, image);
    }

    @CrossOrigin
    @PostMapping(path = "/file-delete/{service}")
    public boolean deleteFile(@PathVariable("service") String serviceName,
                             @RequestBody List<String> filenameList) {
        return this.serviceLifecycle.requestLevel2Service().removeFile(ConstantUtil.UPLOAD_BUCKET, serviceName, filenameList);
    }

    @CrossOrigin
    @GetMapping(path = "/getTaskStatus")
    public List<TaskStatusDto> getTaskStatus() {
        return this.serviceLifecycle.requestM00Codes030Service().getTaskStatus();
    }

    @CrossOrigin
    @GetMapping(path = "/test")
    public String test(@RequestBody String recipient) {
        return this.serviceLifecycle.requestLevel2Service().sendMail(recipient, "", "", "", "");
    }
}
