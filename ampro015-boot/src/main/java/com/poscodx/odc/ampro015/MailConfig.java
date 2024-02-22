package com.poscodx.odc.ampro015;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
