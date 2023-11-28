package com.poscodx.odc.ampro015.service.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceLifecycler implements ServiceLifecycle {

    private final Level2Service level2Service;
    private final AssetService assetService;
    private final FieldService fieldService;
    private final ImageService imageService;
    private final TaskService taskService;

    @Override
    public Level2Service requestLevel2Service() {
        return this.level2Service;
    }

    @Override
    public AssetService requestAssetService() {
        return this.assetService;
    }

    @Override
    public FieldService requestFieldService() {
        return this.fieldService;
    }

    @Override
    public ImageService requestImageService() {
        return this.imageService;
    }

    @Override
    public TaskService requestTaskService() {
        return this.taskService;
    }
}
