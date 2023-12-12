package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Dashboard;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

public interface Level2DashboardService {
    Pme00Dashboard loadDashboard(ServiceLifecycle serviceLifecycle);
}
