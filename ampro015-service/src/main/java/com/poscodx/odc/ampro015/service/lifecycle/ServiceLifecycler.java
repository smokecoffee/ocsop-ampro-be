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
    private final Pme00MemberService pme00MemberService;
    private final Pme00EmployeeMeetingService pme00EmployeeMeetingService;
    private final TaskService taskService;
    private final Pme00MeetingService pme00MeetingService;
    private final M00Codes030Service m00Codes030Service;

    @Override
    public Level2Service requestLevel2Service() {
        return this.level2Service;
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
    public Pme00MemberService requestPme00MemberService() {
        return this.pme00MemberService;
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
    public TaskService requestTaskService() {
        return this.taskService;
    }

}
