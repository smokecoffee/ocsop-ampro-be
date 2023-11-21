/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-service
 * Source File Name   : com.posco.mes3.ra.proxy.SampleProxyProxyLogic
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
package com.poscodx.odc.ampro015.service.bind;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import com.poscdx.odc.ampro015.domain.proxy.SampleProxyProxy;

/**
 * SampleProxyProxyLogic.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@Service
@EnableFeignClients(basePackages = {"com.posco.mes3"})
public class SampleProxyDelegator implements SampleProxyProxy {

}