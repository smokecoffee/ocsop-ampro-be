package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.Constants;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class Level2Logic implements Level2Service {

    @Override
    public String renderQRcode(String token) {
        QRCodeRender qrCodeRender = new QRCodeRender();
        return qrCodeRender.generateEmbeddedQRCodenBase64(token);
    }


    @Override
    public String uploadFile(String serviceName, MultipartFile image) {
        String projectPath = System.getProperty("user.dir");
        String fileName = Calendar.getInstance().getTime().getTime() + image.getOriginalFilename();
        Path root = Paths.get(projectPath + Constants.UPLOAD_FILE_PATH + serviceName + "\\" + fileName);
        try {
            image.transferTo(root);
        } catch (Exception e) {
            return "Upload unsuccessfully!";
        }
        return fileName;
    }
}
