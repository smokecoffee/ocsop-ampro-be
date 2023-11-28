package com.poscodx.odc.ampro015.store.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.StoreLifecycle;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreLifecycler implements StoreLifecycle {

    private final AssetStore assetStore;
    private final FieldStore fieldStore;
    private final ImageStore imageStore;

    @Override
    public AssetStore requestAssetStore() {
        return this.assetStore;
    }

    @Override
    public FieldStore requestFieldStore() {
        return this.fieldStore;
    }

    @Override
    public ImageStore requestImageStore() {
        return this.imageStore;
    }


}
