package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllLevel2EmployeeResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class Pme00EmployeeResource {

    private final ServiceLifecycler serviceLifecycle;

    @GetMapping("/")
    @CrossOrigin
    public Pme00AllLevel2EmployeeResponse getListPmeEmployee() {
        return this.serviceLifecycle.requestLevel2EmployeeService().getListPmeEmployee(serviceLifecycle);
    }

    @GetMapping("/search")
    @CrossOrigin
    public Pme00AllLevel2EmployeeResponse searchPmeEmployee(@RequestParam(required = false, name = "site") String site,
                                                            @RequestParam(required = false, name = "status") String status,
                                                            @RequestParam(required = false, name = "name") String name){
        return this.serviceLifecycle.requestLevel2EmployeeService().searchPmeEmployee(serviceLifecycle, site, status, name);
    }


}
