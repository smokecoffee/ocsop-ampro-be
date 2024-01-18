package com.poscdx.odc.ampro015.domain.spec;

import org.springframework.web.multipart.MultipartFile;

public interface UtilsService {

    String uploadFile(String serviceName, MultipartFile file);
}
