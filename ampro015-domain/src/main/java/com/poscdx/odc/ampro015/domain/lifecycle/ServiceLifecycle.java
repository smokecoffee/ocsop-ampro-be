package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeMeetingService;
import com.poscdx.odc.ampro015.domain.spec.Pme00MemberService;
import com.poscdx.odc.ampro015.domain.spec.TaskService;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();

    AssetService requestAssetService();
    FieldService requestFieldService();
    ImageService requestImageService();
    Pme00MemberService requestPme00MemberService();
    Pme00EmployeeMeetingService requestPme00EmployeeMeetingService();
    TaskService requestTaskService();
}
