package com.poscdx.odc.ampro015.domain.spec;

import org.springframework.web.multipart.MultipartFile;

public interface Level2Service {

    String renderQRcode(String token);

    String uploadFile(String serviceName, MultipartFile image);


}
