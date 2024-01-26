package com.poscdx.odc.ampro015.domain.utils;

public class ConstantUtil {

    public static final int CD_TP_ID = 63;

    public static final int CATEGORY_GROUP_ID = 56;

    public static String UPLOAD_URL = "";
    public static String UPLOAD_BUCKET = "";

    public static String MAIL_HOST = "116.123.216.77";
//    public static String MAIL_ADDRESS = "no-reply@posco.com";
    public static String MAIL_ADDRESS = "no-reply@posco.com";
    public static String MAIL_NAME = "ODC데이터포털";

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
