package com.poscodx.odc.ampro015.store.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.StoreLifecycle;
import com.poscdx.odc.ampro015.domain.store.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreLifecycler implements StoreLifecycle {

    private final AssetStore assetStore;
    private final FieldStore fieldStore;
    private final ImageStore imageStore;
    private final M00Codes030Store m00Codes030Store;
    private final TaskStore taskStore;

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

    @Override
    public M00Codes030Store requestM00Codes030Store(){
        return this.m00Codes030Store;
    }

    @Override
    public TaskStore requestTaskStore() {
        return this.taskStore;
    }
}
