package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.M00Codes030Service;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import com.poscdx.odc.ampro015.domain.store.M00Codes030Store;

public interface StoreLifecycle {
    AssetStore requestAssetStore();
    FieldStore requestFieldStore();
    ImageStore requestImageStore();

    M00Codes030Store requestM00Codes030Store();
}
