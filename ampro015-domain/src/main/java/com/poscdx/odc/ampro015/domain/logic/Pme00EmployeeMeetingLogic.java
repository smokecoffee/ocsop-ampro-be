package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeetingId;
import com.poscdx.odc.ampro015.domain.spec.Pme00EmployeeMeetingService;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeMeetingStore;

import java.util.List;

public class Pme00EmployeeMeetingLogic implements Pme00EmployeeMeetingService {
    private final Pme00EmployeeMeetingStore store;

    public Pme00EmployeeMeetingLogic(Pme00EmployeeMeetingStore store) {
        this.store = store;
    }

    @Override
    public Pme00EmployeeMeeting find(Pme00EmployeeMeetingId entityId) {
        return this.store.retrieve(entityId);
    }

    @Override
    public List<Pme00EmployeeMeeting> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00EmployeeMeeting> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00EmployeeMeeting register(Pme00EmployeeMeeting entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(Pme00EmployeeMeetingId entityId) {
        this.store.delete(entityId);
    }

}
