package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.spec.Level2QrCodeService;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.Level2TaskService;
import com.poscdx.odc.ampro015.domain.spec.M00Codes030Service;
import com.poscdx.odc.ampro015.domain.spec.M00TaskService;
import com.poscdx.odc.ampro015.domain.spec.Pme00AnnouncementService;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeMeetingService;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeTaskService;
import com.poscdx.odc.ampro015.domain.spec.Pme00MeetingService;
import com.poscdx.odc.ampro015.domain.spec.Pme00MemberService;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;

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

    Pme00EmployeeTaskService requestPme00EmployeeTaskService();

    Level2ProjectService requestLevel2ProjectService();
    Level2QrCodeService requestLevel2QrCodeService();
    Level2TaskService requestLevel2TaskService();
}
