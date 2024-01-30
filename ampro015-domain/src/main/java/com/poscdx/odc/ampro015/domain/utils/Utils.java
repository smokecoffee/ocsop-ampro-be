package com.poscdx.odc.ampro015.domain.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {


    public static final int MAX_EXPIRE_TOKEN = 24;// for expire token reset pass with max expire time by hour
    public static final int CD_TP_ID = 63;

    public static final int CATEGORY_GROUP_ID = 56;

    public static String UPLOAD_URL = "";
    public static String UPLOAD_BUCKET = "";

    public static String MAIL_SMTP_SERVER = "116.123.216.77";
    public static String MAIL_SMTP_SERVER_PORT = "25";
    public static String MAIL_SMTP_EMAIL_ID = "no-reply@example.com";
    public static String MAIL_SMTP_EMAIL_ID_ALIAS = "ODC데이터포털";
    public static String MAIL_SMTP_EMAIL_USERNAME = "ss";
    public static String MAIL_SMTP_EMAIL_PASSWORD = "ss";
    public static String MAIL_FRONT_END_URL = "http://localhost:3000";

    public static String applyEmployeeAvatarPath(String avatar, String serviceName) {
        if (!serviceName.isEmpty()) {
            serviceName += "/";
        }
        return (avatar == null || avatar.isEmpty()) ? null :
                Utils.UPLOAD_URL + Utils.UPLOAD_BUCKET + "/" + serviceName + avatar;
    }
    public static void removeAvatarPath(String avatar, String serviceName) {
        avatar = avatar.replace(Utils.UPLOAD_URL, "")
                       .replace(Utils.UPLOAD_BUCKET, "");
        if (avatar.lastIndexOf(serviceName) > 0) {
            avatar = avatar.replaceFirst(serviceName + "/" , "");
        }
    }
    public static List<String> getPermissionList() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.stream().map(Object::toString).collect(Collectors.toList());
    }
}
