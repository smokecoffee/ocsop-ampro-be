package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;

import java.util.Date;
import java.util.List;

public interface Pme00MeetingStore {

    public Pme00Meeting retrieve(int id);

    public List<Pme00Meeting> retrieveAll();

    public Pme00Meeting update(Pme00Meeting pme00Meeting);

    public Pme00Meeting create(Pme00Meeting entity);

    public void delete(int id);

    public List<Pme00Meeting> findAllByAssetId(Integer cd_tp_id, String title, String startTime, String endTime, String creatorId, String requesterId, String categoryMeeting, String status);
}
