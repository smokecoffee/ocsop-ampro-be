package com.poscdx.odc.ampro015.domain.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "email")
@ConstructorBinding
@RequiredArgsConstructor
public class MailConfig {

    public String smtpHostServer;

    @Value("${mail.smtpHostServer}")
    public void setMailSmtpServer(String mailSmtpServer) {
        ConstantUtil.MAIL_SMTP_SERVER = mailSmtpServer;
    }
    @Value("${mail.smtpHostServerPort}")
    public void setMailSmtpServerPort(String mailSmtpServerPort) {
        ConstantUtil.MAIL_SMTP_SERVER_PORT = mailSmtpServerPort;
    }
    @Value("${mail.emailId}")
    public void setMailSmtpEmailId(String mailSmtpEmailId) {
        ConstantUtil.MAIL_SMTP_EMAIL_ID = mailSmtpEmailId;
    }
    @Value("${mail.emailIdAlias}")
    public void setMailSmtpEmailIdAlias(String mailSmtpEmailIdAlias) {
        ConstantUtil.MAIL_SMTP_EMAIL_ID_ALIAS = mailSmtpEmailIdAlias;
    }

    @Value("${mail.emailPass}")
    public void setMailSmtpEmailPassword(String mailSmtpEmailPassword) {
        ConstantUtil.MAIL_SMTP_EMAIL_PASSWORD = mailSmtpEmailPassword;
    }
    @Value("${mail.frontEndUrl}")
    public void setFrontEndUrl(String frontEndUrl) {
        ConstantUtil.MAIL_FRONT_END_URL = frontEndUrl;
    }
}
