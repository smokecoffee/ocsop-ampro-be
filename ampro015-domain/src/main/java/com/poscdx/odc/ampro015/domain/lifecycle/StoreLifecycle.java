package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.store.*;

public interface StoreLifecycle {

    QCodeItemStore requestQCodeItemStore();
    SCodeItemStore requestSCodeItemStore();

    AssetStore requestAssetStore();
    FieldStore requestFieldStore();
    ImageStore requestImageStore();
}
