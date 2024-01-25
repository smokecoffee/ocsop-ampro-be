package com.poscdx.odc.ampro015.domain.utils;

public class ConstantUtil {

    public static final int CD_TP_ID = 63;

    public static final int CATEGORY_GROUP_ID = 56;

    public static String UPLOAD_URL = "";
    public static String UPLOAD_BUCKET = "";
//    public static String UPLOAD_PATH = UPLOAD_URL + UPLOAD_BUCKET + "/";

    public static String applyEmployeeAvatarPath(String avatar, String serviceName) {
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
