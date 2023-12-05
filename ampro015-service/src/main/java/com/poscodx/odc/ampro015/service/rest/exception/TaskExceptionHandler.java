/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-service
 * Source File Name   : com.posco.mes3.sp.rest.exception.ProductHistoryExceptionHandler
 * Description        :
 * Author             : poscouser
 * Version            : 1.0.0
 * File Name related  :
 * Class Name related :
 * Created Date       : ${date}
 * Updated Date       : ${date}
 * Last modifier      : poscouser
 * Updated content    : 최초작성
 *
 *==================================================================================*/
package com.poscodx.odc.ampro015.service.rest.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.posco.reuse.common.errorobjects.PosBaseException;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ProductHistoryExceptionHandler.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(PosBaseException.class)
    public ResponseEntity<String> handlePosBaseException(PosBaseException exception) {

        PosLogger.developerLog(PosLogWriterIF.ERROR, "=====handlePosBaseException=====", this);
        logException(exception);
        ResponseEntity retVal = ResponseEntity.status(exception.getCode()).body(exception.getMessage());
        return retVal;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        PosLogger.developerLog(PosLogWriterIF.ERROR, "=====handleRuntimeException=====", this);
        logException(exception);
        PosBaseException posBaseException = new PosBaseException(exception);
        ResponseEntity retVal = ResponseEntity.status(posBaseException.getCode()).body(posBaseException.getMessage());

        return retVal;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        PosLogger.developerLog(PosLogWriterIF.ERROR, "=====handleException=====", this);
        logException(exception);
        PosBaseException posBaseException = new PosBaseException(exception);

        ResponseEntity retVal = ResponseEntity.status(posBaseException.getCode()).body(posBaseException.getMessage());

        return retVal;
    }

    
    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleJsonProcessingException(Exception exception) {
        PosLogger.developerLog(PosLogWriterIF.ERROR, "=====handleJsonProcessingException=====", this);
        logException(exception);
        PosBaseException posBaseException = new PosBaseException(exception);

        ResponseEntity retVal = ResponseEntity.status(posBaseException.getCode()).body(posBaseException.getMessage());

        return retVal;
    }

    private void logException(Exception e) {
        PosLogger.developerLog(PosLogWriterIF.ERROR, e, this);
    }
}
