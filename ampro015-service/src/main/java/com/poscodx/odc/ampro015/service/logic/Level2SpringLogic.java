package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Level2Logic;
import io.minio.MinioClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Level2SpringLogic extends Level2Logic {
    public Level2SpringLogic(MinioClient minioClient) { super(minioClient); }
}