package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse; <--- not use, pls remove
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.SearchMeetingDto;<--- not use, pls remove

import java.util.Date;
import java.util.List;

add comment
public interface Pme00MeetingService {

    add comment
    Pme00Meeting find(int id);

    add comment
    List<Pme00Meeting> findAll();

    add comment
    void modify(List<Pme00Meeting> entityList);

    add comment
    Pme00Meeting register(Pme00Meeting entity);

    add comment
    void remove(int id);

    add comment
    List<Pme00Meeting> findAllByAssetId(
                                int cd_tp_id,
                                String title,
                                String startTime,
                                String endTime,
                                String creatorId,
                                String requesterId,
                                String categoryMeeting,
                                String status);

    add comment
    int findMetingByStartAndEnd(int cdTpId, Date startDate, Date endDate);

}
