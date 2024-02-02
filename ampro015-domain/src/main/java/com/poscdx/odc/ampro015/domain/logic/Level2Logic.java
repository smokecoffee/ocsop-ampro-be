package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.MailSender;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class Level2Logic implements Level2Service {

    private final MinioClient minioClient;

    @Override
    public String renderQRcode(String token) {
        QRCodeRender qrCodeRender = new QRCodeRender();
        return qrCodeRender.generateEmbeddedQRCodenBase64(token);
    }

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

    @Override
    public boolean removeFile(String bucketName, String serviceName, List<String> filenameList) {
        try {
            List<String> deleteList = new ArrayList<>();
            filenameList.forEach(filename -> deleteList.add("/" + serviceName + "/" + filename));

            for (String deleteString : deleteList) {
                minioClient.removeObject(bucketName, deleteString);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String sendMail(String recipient, String username, String password, String subject, String body) {
         MailSender.sendEmailWithAuthentication("", "Your email subject", "<b>Hello, this is the body of the email.</b>");
    return "OK";
    }

    @Override
    public boolean sendMail(String mail,String subject, String resetHtmlTemplate) {
        return MailSender.sendEmailWithAuthentication(mail, subject, resetHtmlTemplate);
    }
}
