package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSettingDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

public interface Level3DashboardService {
    Pme00DashboardSettingDto loadDashboard(ServiceLifecycle serviceLifecycle, String empId);
    Pme00DashboardSettingDto modifyDashboard(ServiceLifecycle serviceLifecycle, Pme00DashboardSetting entity);
}
