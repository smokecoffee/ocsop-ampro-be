package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@RequiredArgsConstructor
public class Level2Logic implements Level2Service {

    private final MinioClient minioClient;

    @Override
    public String renderQRcode(String token) {
        QRCodeRender qrCodeRender = new QRCodeRender();
        return qrCodeRender.generateEmbeddedQRCodenBase64(token);
    }

//    @Override
//    public String uploadFile(String serviceName, MultipartFile image) {
//        String fileName = image.getOriginalFilename();
//        Path root = Paths.get(Constants.UPLOAD_FILE_PATH + serviceName + "\\" + fileName);
//        try {
//            image.transferTo(root);
//        } catch (Exception e) {
//            return "Upload unsuccessfully!";
//        }
//        return fileName;
//    }

@Override
public String uploadFile(String bucketName, String serviceName, MultipartFile file) {
    try {
        final String fileName = serviceName + "/" + file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(file.getContentType())
                .build());
        return fileName;
    } catch (Exception e) {
        return "Upload unsuccessfully!";
    }
}
}
