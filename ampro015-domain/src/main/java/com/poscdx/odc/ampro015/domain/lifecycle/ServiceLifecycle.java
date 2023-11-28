package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.*;

public interface ServiceLifecycle {
    Level2Service requestLevel2Service();

    AssetService requestAssetService();
    FieldService requestFieldService();
    ImageService requestImageService();

    Pme00MemberService requestPme00MemberService();

    Pme00EmployeeMeetingService requestPme00EmployeeMeetingService();
}
