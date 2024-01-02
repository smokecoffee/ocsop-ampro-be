package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;

import java.util.List;

public interface Pme00DashboardSettingStore {
    Pme00DashboardSetting retrieve(int id);

    List<Pme00DashboardSetting> retrieveAll();

    Pme00DashboardSetting update(Pme00DashboardSetting Pme00DashboardSetting);

    Pme00DashboardSetting create(Pme00DashboardSetting entity);

    void delete(int id);

    Pme00DashboardSetting findByEmpId(String empId);
}
