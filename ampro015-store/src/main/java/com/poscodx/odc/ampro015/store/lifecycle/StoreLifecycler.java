package com.poscodx.odc.ampro015.store.lifecycle;

import com.poscdx.odc.ampro015.domain.lifecycle.StoreLifecycle;
import com.poscdx.odc.ampro015.domain.store.QCodeItemStore;
import com.poscdx.odc.ampro015.domain.store.SCodeItemStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreLifecycler implements StoreLifecycle {

    private final QCodeItemStore qCodeItemStore;
    private final SCodeItemStore sCodeItemStore;

    @Override
    public QCodeItemStore requestQCodeItemStore() {
        return this.qCodeItemStore;
    }

    @Override
    public SCodeItemStore requestSCodeItemStore() {
        return this.sCodeItemStore;
    }
}
