package com.poscodx.odc.ampro015;

import io.minio.MinioClient;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Getter
@Setter
public class MailConfig {
    @Value("${mail.smtp.host}")
    private String SmtpHostServer;
    @Value("${mail.smtp.port}")
    private String SmtpHostServerPort;
    @Value("${mail.smtp.id}")
    private String EmailId;
    @Value("${mail.smtp.name}")
    private String EmailName;
    @Value("${mail.smtp.userName}")
    private String UserName;
    @Value("${mail.smtp.password}")
    private String Password;
    @Value("${mail.frontEndUrl}")
    private String FrontEndUrl;
    @Value("${mail.subject}")
    private String Subject;

}
