package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.spec.Pme00AnnouncementService;
import com.poscdx.odc.ampro015.domain.store.Pme00AnnouncementStore;

import java.util.Date;
import java.util.List;

public class Pme00AnnouncementLogic implements Pme00AnnouncementService {
    private final Pme00AnnouncementStore store;

    public Pme00AnnouncementLogic(Pme00AnnouncementStore store) {
        this.store = store;
    }

    @Override
    public Pme00Announcement find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00Announcement> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public List<Pme00Announcement> findByConditions(String name, int type, String status, Date date) {
        return this.store.retrieveByConditions(name, type, status, date);
    }

    @Override
    public void modify(List<Pme00Announcement> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00Announcement register(Pme00Announcement entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

}
