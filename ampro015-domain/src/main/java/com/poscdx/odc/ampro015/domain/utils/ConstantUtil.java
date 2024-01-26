package com.poscdx.odc.ampro015.domain.utils;

public class ConstantUtil {


    public static final int MAX_EXPIRE_TOKEN = 24;// for expire token reset pass with max expire time by hour
    public static final int CD_TP_ID = 63;

    public static final int CATEGORY_GROUP_ID = 56;

    public static String UPLOAD_URL = "";
    public static String UPLOAD_BUCKET = "";
//    public static String UPLOAD_PATH = UPLOAD_URL + UPLOAD_BUCKET + "/";

    public static String MAIL_SMTP_SERVER = "";
    public static String MAIL_SMTP_SERVER_PORT = "";
    public static String MAIL_SMTP_EMAIL_ID = "";
    public static String MAIL_SMTP_EMAIL_ID_ALIAS = "";
    public static String MAIL_SMTP_EMAIL_PASSWORD = "";
    public static String MAIL_FRONT_END_URL = "http://localhost:3000";

    public static String applyEmployeeAvatarPath(String avatar, String serviceName) {
        if (!serviceName.isEmpty()) {
            serviceName += "/";
        }
        return (avatar == null || avatar.isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/" + serviceName + avatar;
    }
    public static void removeAvatarPath(String avatar, String serviceName) {
        avatar = avatar.replace(ConstantUtil.UPLOAD_URL, "")
                       .replace(ConstantUtil.UPLOAD_BUCKET, "");
        if (avatar.lastIndexOf(serviceName) > 0) {
            avatar = avatar.replaceFirst(serviceName + "/" , "");
        }
    }

}
