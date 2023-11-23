package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.store.*;

public interface StoreLifecycle {

    AssetStore requestAssetStore();
    FieldStore requestFieldStore();
    ImageStore requestImageStore();
    QCodeItemStore requestQCodeItemStore();
    SCodeItemStore requestSCodeItemStore();
}
