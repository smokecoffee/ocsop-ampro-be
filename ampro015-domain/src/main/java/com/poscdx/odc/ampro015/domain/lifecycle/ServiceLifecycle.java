package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.spec.*;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();

    AssetService requestAssetService();
    FieldService requestFieldService();
    ImageService requestImageService();
    M00Codes030Service requestM00Codes030Service();
    M00TaskService requestTaskService();
    Pme00EmployeeMeetingService requestPme00EmployeeMeetingService();

    Pme00MeetingService requestPme00MeetingService();
    Pme00MemberService requestPme00MemberService();
    Pme00ProjectInfoService requestPme00ProjectInfoService();

    Pme00AnnouncementService requestPme00AnnouncementService();
}
