package com.poscodx.odc.ampro015.service.rest;

import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/receive")
public class EaiResource {

    /**
     * receiveEai
     *
     * @param eaiMessage String EAI 전문
     * @return String 수신 결과
     */
    @PostMapping("/eai")
    public String receiveEai(@RequestBody String eaiMessage) {
        //
        PosLogger.developerLog(PosLogWriterIF.INFO, " EAI Message : " + eaiMessage, this);
        return "SUCCESS";
    }
}
