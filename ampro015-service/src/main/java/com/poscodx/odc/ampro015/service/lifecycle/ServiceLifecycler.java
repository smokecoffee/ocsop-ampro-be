package com.poscodx.odc.ampro015.service.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceLifecycler implements ServiceLifecycle {

    private final Level2Service level2Service;
    private final AssetService assetService;
    private final FieldService fieldService;
    private final ImageService imageService;
    private final M00Codes030Service m00Codes030Service;
    private final M00TaskService m00TaskService;
    private final Pme00EmployeeMeetingService pme00EmployeeMeetingService;
    private final Pme00MeetingService pme00MeetingService;
    private final Pme00MemberService pme00MemberService;
    private final Pme00ProjectInfoService pme00ProjectInfoService;
    private final Pme00AnnouncementService pme00AnnouncementService;
    private final Pme00EmployeeTaskService pme00EmployeeTaskService;
    private final Level2MeetingService bookingMeetingRoomService;

    @Override
    public Level2Service requestLevel2Service() {
        return this.level2Service;
    }

    @Override
    public Level2MeetingService bookingMeetingRoomService() {
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
    public M00Codes030Service requestM00Codes030Service(){
        return this.m00Codes030Service;
    }

    @Override
    public M00TaskService requestTaskService() {
        return this.m00TaskService;
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
}
