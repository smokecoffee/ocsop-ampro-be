package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.*;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();
    Level3DashboardService requestLevel3DashboardService();
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
    Pme00DashboardSettingService requestPme00DashboardSettingService();
    Pme00EmployeeTaskService requestPme00EmployeeTaskService();
    Level2MeetingService requestBookingMeetingRoomService();
    M00Codes020Service requestM00Codes020Service();
    Level2ProjectService requestLevel2ProjectService();
    Level2QrCodeService requestLevel2QrCodeService();
    Level2TaskService requestLevel2TaskService();
}
