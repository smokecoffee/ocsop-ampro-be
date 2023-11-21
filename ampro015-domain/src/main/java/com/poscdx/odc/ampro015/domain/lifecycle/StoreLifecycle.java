package com.poscdx.odc.ampro015.domain.lifecycle;

import com.poscdx.odc.ampro015.domain.store.QCodeItemStore;
import com.poscdx.odc.ampro015.domain.store.SCodeItemStore;

public interface StoreLifecycle {
    QCodeItemStore requestQCodeItemStore();
    SCodeItemStore requestSCodeItemStore();
}
