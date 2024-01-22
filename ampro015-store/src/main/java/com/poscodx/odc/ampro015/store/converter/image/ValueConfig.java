package com.poscodx.odc.ampro015.store.converter.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueConfig {

    public static String MINIO_URL;
    public static String BUCKET_NAME;
    public static String DB_SCHEMA;

    @Value("${minio.url}")
    public void setUrl(String url) {
        MINIO_URL = url;
    }

    @Value("${minio.bucketName}")
    public void setBucketName(String bucketName) {
        BUCKET_NAME = bucketName;
    }


    @Value("${datasource.schema}")
    public void setDbSchema(String dbSchema) {
        DB_SCHEMA = dbSchema;
    }
}
