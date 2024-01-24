package com.poscdx.odc.ampro015.domain.spec;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Level2Service {

    String renderQRcode(String token);

    String uploadFile(String bucketName, String serviceName, MultipartFile image);

    String removeFile(String bucketName, String serviceName, List<String> fileName);

}
