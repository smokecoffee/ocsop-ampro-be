package com.poscodx.odc.ampro015.service.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.spec.QCodeItemService;
import com.poscdx.odc.ampro015.domain.spec.SCodeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceLifecycler implements ServiceLifecycle {

    private final QCodeItemService qCodeItemService;
    private final SCodeItemService sCodeItemService;
    private final Level2Service level2Service;

    @Override
    public QCodeItemService requestQCodeItemService() {
        return this.qCodeItemService;
    }

    @Override
    public SCodeItemService requestSCodeItemService() {
        return this.sCodeItemService;
    }

    @Override
    public Level2Service requestLevel2Service() {
        return this.level2Service;
    }
}
