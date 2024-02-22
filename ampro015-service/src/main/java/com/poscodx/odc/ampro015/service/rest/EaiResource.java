package com.poscodx.odc.ampro015.service.rest;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/receive")
@Log4j2
public class EaiResource {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EaiResource.class);
    /**
     * receiveEai
     *
     * @param eaiMessage String EAI 전문
     * @return String 수신 결과
     */
    @PostMapping("/eai")
    public String receiveEai(@RequestBody String eaiMessage) {
        logger.trace(" EAI Message : {}", eaiMessage);
        return "SUCCESS";
    }
}
