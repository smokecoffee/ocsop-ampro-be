package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;

import java.util.List;

public interface Pme00AnnouncementService {
    Pme00Announcement find(int id);

    List<Pme00Announcement> findAll();

    void modify(List<Pme00Announcement> entityList);

    Pme00Announcement register(Pme00Announcement entity);

    void remove(int id);
}
