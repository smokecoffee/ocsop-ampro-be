package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;

import java.util.List;

public interface Pme00DashboardSettingService {
    Pme00DashboardSetting find(int id);

    List<Pme00DashboardSetting> findAll();

    Pme00DashboardSetting modify(Pme00DashboardSetting entity);

    Pme00DashboardSetting register(Pme00DashboardSetting entity);

    void remove(int id);

    Pme00DashboardSetting findByEmpId(String empId);
}
