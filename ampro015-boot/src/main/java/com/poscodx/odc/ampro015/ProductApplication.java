/*===================================================================================
 *                    Copyright(c) ${year} POSCO
 *
 * Project            : business-boot
 * Source File Name   : com.posco.mes3.ProductHistoryApplication
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
package com.poscodx.odc.ampro015;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@RestController
//@EnableDiscoveryClient
@EnableWebMvc
//@EnableFeignClients(basePackages = {"com.posco.mes3.reusecompensation.client"})
public class ProductApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductApplication.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

}
