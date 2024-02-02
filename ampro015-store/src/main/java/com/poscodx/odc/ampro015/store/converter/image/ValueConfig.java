package com.poscodx.odc.ampro015.store.converter.image;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueConfig {

    @Value("${minio.url}")
    public void setUrl(String url) {
        Utils.UPLOAD_URL = url;
    }

    @Value("${minio.bucketName}")
    public void setBucketName(String bucketName) {
        Utils.UPLOAD_BUCKET = bucketName;
    }
    
}
