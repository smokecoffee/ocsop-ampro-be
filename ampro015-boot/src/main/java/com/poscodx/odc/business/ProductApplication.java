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
package com.poscodx.odc.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ProductHistoryApplication.java
 *
 * @author poscouser
 * @version 1.0.0
 * @since ${date}
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.posco.mes3.business", "com.posco.reuse.compensation.util", })
@RestController
@EnableDiscoveryClient
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
