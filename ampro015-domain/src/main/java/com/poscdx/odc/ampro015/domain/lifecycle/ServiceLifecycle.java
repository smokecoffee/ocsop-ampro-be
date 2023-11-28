package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.TaskService;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();

    AssetService requestAssetService();
    FieldService requestFieldService();
    ImageService requestImageService();
    TaskService requestTaskService();
}
