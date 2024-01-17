package com.poscodx.odc.ampro015.service.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceLifecycler implements ServiceLifecycle {

    private final Level2ProjectService level2ProjectService;
    private final Level2Service level2Service;
    private final Level3DashboardService level3DashboardService;
    private final AssetService assetService;
    private final FieldService fieldService;
    private final ImageService imageService;
    private final M99DailyReportService m99DailyReportService;
    private final M00Codes030Service m00Codes030Service;
    private final M00TaskService m00TaskService;
    private final M00EmployeeService m00EmployeeService;
    private final Pme00EmployeeMeetingService pme00EmployeeMeetingService;
    private final Pme00MeetingService pme00MeetingService;
    private final Pme00MemberService pme00MemberService;
    private final Pme00ProjectInfoService pme00ProjectInfoService;
    private final Pme00AnnouncementService pme00AnnouncementService;
    private final Pme00DashboardSettingService pme00DashboardSettingService;
    private final Pme00EmployeeTaskService pme00EmployeeTaskService;
    private final Level2MeetingService bookingMeetingRoomService;
    private final M00Codes020Service m00Codes020Service;
    private final Pme00RoleService pme00RoleService;
    private final Pme00RoleUserService pme00RoleUserService;
    private final Pme00PermissionService pme00PermissionService;
    private final Pme00PerRoleService pme00PerRoleService;
    private final Level2TaskService level2TaskService;
    private final Level2QrCodeService level2QrCodeService;

    @Override
    public Level2ProjectService requestLevel2ProjectService() { return this.level2ProjectService; }

    @Override
    public Level2Service requestLevel2Service() {
        return this.level2Service;
    }

    @Override
    public Level3DashboardService requestLevel3DashboardService() {
        return this.level3DashboardService;
    }
     @Override
    public Level2MeetingService requestBookingMeetingRoomService() {
        return this.bookingMeetingRoomService;
    }

    @Override
    public AssetService requestAssetService() {
        return this.assetService;
    }

    @Override
    public FieldService requestFieldService() {
        return this.fieldService;
    }

    @Override
    public ImageService requestImageService() {
        return this.imageService;
    }

    @Override
    public M99DailyReportService requestM99DailyReportService() {
        return this.m99DailyReportService;
    }

    @Override
    public M00Codes030Service requestM00Codes030Service(){
        return this.m00Codes030Service;
    }

    @Override
    public M00TaskService requestM00TaskService() {
        return this.m00TaskService;
    }

    @Override
    public M00EmployeeService requestM00EmployeeService() {
        return this.m00EmployeeService;
    }

    @Override
    public Pme00EmployeeMeetingService requestPme00EmployeeMeetingService() {
        return this.pme00EmployeeMeetingService;
    }

    @Override
    public Pme00MeetingService requestPme00MeetingService() {
        return this.pme00MeetingService;
    }

    @Override
    public Pme00MemberService requestPme00MemberService() {
        return this.pme00MemberService;
    }

    @Override
    public Pme00ProjectInfoService requestPme00ProjectInfoService() {
        return this.pme00ProjectInfoService;
    }

    @Override
    public Pme00EmployeeTaskService requestPme00EmployeeTaskService() {
        return this.pme00EmployeeTaskService;
    }

    @Override
    public Pme00AnnouncementService requestPme00AnnouncementService() { return this.pme00AnnouncementService; }

    @Override
    public Pme00DashboardSettingService requestPme00DashboardSettingService() {
        return this.pme00DashboardSettingService;
    }

    @Override
    public M00Codes020Service requestM00Codes020Service(){return this.m00Codes020Service;};

    @Override
    public Level2TaskService requestLevel2TaskService() {
        return this.level2TaskService;
    }

    @Override
    public Pme00RoleService requestPme00RoleService() {
        return this.pme00RoleService;
    }

    @Override
    public Pme00RoleUserService requestPme00RoleUserService() {
        return this.pme00RoleUserService;
    }

    @Override
    public Pme00PermissionService requestPme00PermissionService() {
        return this.pme00PermissionService;
    }

    @Override
    public Pme00PerRoleService requestPme00PerRoleService() {
        return this.pme00PerRoleService;
    }

    @Override
    public Level2QrCodeService requestLevel2QrCodeService() {
        return this.level2QrCodeService;
    }
}
