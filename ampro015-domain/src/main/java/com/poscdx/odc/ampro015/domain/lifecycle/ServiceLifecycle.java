package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.*;

public interface ServiceLifecycle {
    QCodeItemService requestQCodeItemService();

    SCodeItemService requestSCodeItemService();

    Level2Service requestLevel2Service();

    AssetService requestAssetService();

    FieldService requestFieldService();
    
    ImageService requestImageService();
}
