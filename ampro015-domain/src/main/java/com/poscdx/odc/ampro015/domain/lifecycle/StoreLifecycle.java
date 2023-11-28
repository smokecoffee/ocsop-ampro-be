package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscdx.odc.ampro015.domain.store.ImageStore;

public interface StoreLifecycle {
    AssetStore requestAssetStore();
    FieldStore requestFieldStore();
    ImageStore requestImageStore();
}
