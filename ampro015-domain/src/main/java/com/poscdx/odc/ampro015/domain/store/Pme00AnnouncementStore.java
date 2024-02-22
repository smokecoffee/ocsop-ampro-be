package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;

import java.util.Date;
import java.util.List;

public interface Pme00AnnouncementStore {
    Pme00Announcement retrieve(int id);

    List<Pme00Announcement> retrieveAll();

    List<Pme00Announcement> retrieveByConditions(String name, int type, String status, Date date);

    Pme00Announcement update(Pme00Announcement Pme00Announcement);

    Pme00Announcement create(Pme00Announcement entity);

    void delete(int id);

}