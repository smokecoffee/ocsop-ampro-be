package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.*;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();

    AssetService requestAssetService();
    FieldService requestFieldService();
    ImageService requestImageService();

    M00Codes030Service requestM00Codes030Service();
}
