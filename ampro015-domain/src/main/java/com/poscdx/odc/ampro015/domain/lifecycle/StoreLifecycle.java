package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.store.*;

public interface StoreLifecycle {
    AssetStore requestAssetStore();
    FieldStore requestFieldStore();
    ImageStore requestImageStore();
    M00Codes030Store requestM00Codes030Store();
    M00TaskStore requestTaskStore();
    Pme00EmployeeMeetingStore requestPme00EmployeeMeetingStore();
    Pme00MeetingStore requestMeetingStore();
    Pme00MemberStore requestMemberStore();
    Pme00ProjectInfoStore requestPme00ProjectInfoStore();
}
