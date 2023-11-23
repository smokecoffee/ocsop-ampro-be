package com.poscodx.odc.ampro015.store.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.StoreLifecycle;
import com.poscdx.odc.ampro015.domain.store.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreLifecycler implements StoreLifecycle {

    private final QCodeItemStore qCodeItemStore;
    private final SCodeItemStore sCodeItemStore;
    private final AssetStore assetStore;
    private final FieldStore fieldStore;
    private final ImageStore imageStore;

    @Override
    public AssetStore requestAssetStore() {
        return assetStore;
    }

    @Override
    public FieldStore requestFieldStore() {
        return fieldStore;
    }

    @Override
    public ImageStore requestImageStore() {
        return imageStore;
    }

    @Override
    public QCodeItemStore requestQCodeItemStore() {
        return this.qCodeItemStore;
    }

    @Override
    public SCodeItemStore requestSCodeItemStore() {
        return this.sCodeItemStore;
    }
}
