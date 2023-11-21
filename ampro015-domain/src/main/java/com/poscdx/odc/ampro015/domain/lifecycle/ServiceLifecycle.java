package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.QCodeItemService;
import com.poscdx.odc.ampro015.domain.spec.SCodeItemService;

public interface ServiceLifecycle {
    QCodeItemService requestQCodeItemService();
    SCodeItemService requestSCodeItemService();

    Level2Service requestLevel2Service();
}
