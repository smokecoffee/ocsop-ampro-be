package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;

import java.util.List;

public interface Pme00MeetingService {
    Pme00Meeting find(int id);

    List<Pme00Meeting> findAll();

    void modify(List<Pme00Meeting> entityList);

    Pme00Meeting register(Pme00Meeting entity);

    void remove(int id);


}
