package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.EmployeeDto;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @CrossOrigin
    @GetMapping(path = "/getTaskStatus")
    public List<TaskStatusDto> getTaskStatus() {
        return this.serviceLifecycle.requestPme00ProjectInfoService().getTaskStatus();
    }
}
