package com.poscdx.odc.ampro015.domain.utils;

public class Constants {

    public static final String UPLOAD_STATIC_ASSET_FOLDER_PATH = System.getProperty("user.dir") + "\\static\\Assets\\";
    public static final String UPLOAD_FILE_PATH = System.getProperty("user.dir") +
                                "\\ampro015-boot\\src\\main\\resources\\UploadedFiles\\";
    public static final int TASK_CD_TP_ID = 64;
    public static final int PROJECT_CD_TP_ID = 63;
    public static final int MEETING_ROOM_CD_TP_ID = 63;



    public static final String UPLOAD_FILE_PATH_EXAM_IMAGES = System.getProperty("user.dir") +
            "\\excan013-boot\\src\\main\\resources\\UploadedFiles\\";

    public static final String UPLOAD_STATIC_USER_FOLDER_PATH = System.getProperty("user.dir") + "\\static\\user-management\\";

    public static final String UPLOAD_FILE_PATH_USER = System.getProperty("user.dir") +
            "\\excan013-boot\\src\\main\\resources\\static\\images\\";
    public static String getUploadPath(String serviceName) {
        return UPLOAD_FILE_PATH_EXAM_IMAGES + serviceName + "\\";
    }

    public static String getUploadPathUser(String serviceName) {
        return UPLOAD_FILE_PATH_USER + serviceName + "\\";
    }


}
