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
    private final M00TaskStore m00TaskStore;
    private final Pme00EmployeeMeetingStore pme00EmployeeMeetingStore;
    private final Pme00MeetingStore pme00MeetingStore;
    private final Pme00MemberStore pme00MemberStore;
    private final Pme00ProjectInfoStore pme00ProjectInfoStore;

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

    public M00TaskStore requestTaskStore() {
        return this.m00TaskStore;
    }

    @Override
    public Pme00EmployeeMeetingStore requestPme00EmployeeMeetingStore() {
        return this.pme00EmployeeMeetingStore;
    }

    @Override
    public Pme00MeetingStore requestMeetingStore() {
        return this.pme00MeetingStore;
    }

    @Override
    public Pme00MemberStore requestMemberStore() {
        return this.pme00MemberStore;
    }

    @Override
    public Pme00ProjectInfoStore requestPme00ProjectInfoStore() {
        return this.pme00ProjectInfoStore;
    }
}
