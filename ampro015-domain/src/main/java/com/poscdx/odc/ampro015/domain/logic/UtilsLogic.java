package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.spec.UtilsService;
import com.poscdx.odc.ampro015.domain.utils.Constants;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilsLogic implements UtilsService {

    @Override
    public String uploadFile(String serviceName, MultipartFile image) {
        String fileName = image.getOriginalFilename();
        Path root = Paths.get(Constants.getUploadPath(serviceName) + fileName);
        try {
            image.transferTo(root);
        } catch (Exception e) {
            return "Upload unsuccessfully!";
        }
        return fileName;
    }
}
