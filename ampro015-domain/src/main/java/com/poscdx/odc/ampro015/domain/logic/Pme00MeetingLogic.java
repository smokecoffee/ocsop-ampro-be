package com.poscdx.odc.ampro015.domain.logic;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse; <--- not use, pls remove
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.SearchMeetingDto;<--- not use, pls remove
import com.poscdx.odc.ampro015.domain.spec.Pme00MeetingService;
import com.poscdx.odc.ampro015.domain.store.Pme00MeetingStore;

import java.util.Date;
import java.util.List;

add comment
public class Pme00MeetingLogic implements Pme00MeetingService {

    private final Pme00MeetingStore store;
    add comment
    public Pme00MeetingLogic(Pme00MeetingStore store) {
        this.store = store;
    }

    add comment
    @Override
    public Pme00Meeting find(int id) {
        return this.store.retrieve(id);
    }

    add comment
    @Override
    public List<Pme00Meeting> findAll() {
        return this.store.retrieveAll();
    }

    add comment
    @Override
    public void modify(List<Pme00Meeting> entityList) {
        entityList.forEach(this.store::update);
    }

    add comment
    @Override
    public Pme00Meeting register(Pme00Meeting entity) {
        return this.store.create(entity);
    }

    add comment
    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public List<Pme00Meeting> findAllByAssetId(int cd_tp_id,
                                               String title,
                                               String startTime,
                                               String endTime,
                                               String creatorId,
                                               String requesterId,
                                               String categoryMeeting,
                                               String status) {
        return this.store.findAllByAssetId(cd_tp_id,
                                           title,
                                           startTime,
                                           endTime,
                                           creatorId,
                                           requesterId,
                                           categoryMeeting,
                                           status);
    }

    add comment
    @Override
    public int findMetingByStartAndEnd(int cdTpId, Date startDate, Date endDate) {
        return this.store.findMetingByStartAndEnd(cdTpId, startDate, endDate);
    }


}
