package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscdx.odc.ampro015.domain.spec.Pme00DashboardSettingService;
import com.poscdx.odc.ampro015.domain.store.Pme00DashboardSettingStore;

import java.util.List;

public class Pme00DashboardSettingLogic implements Pme00DashboardSettingService {
    private final Pme00DashboardSettingStore store;

    public Pme00DashboardSettingLogic(Pme00DashboardSettingStore store) {
        this.store = store;
    }

    @Override
    public Pme00DashboardSetting find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00DashboardSetting> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(Pme00DashboardSetting entity) {
        this.store.update(entity);
    }

    @Override
    public Pme00DashboardSetting register(Pme00DashboardSetting entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public Pme00DashboardSetting findByEmpId(String empId) {
        return this.store.findByEmpId(empId);
    }

}
