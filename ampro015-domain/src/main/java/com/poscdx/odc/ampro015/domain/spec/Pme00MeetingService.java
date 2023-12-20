package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.SearchMeetingDto;

import java.util.Date;
import java.util.List;

public interface Pme00MeetingService {
    Pme00Meeting find(int id);

    List<Pme00Meeting> findAll();

    void modify(List<Pme00Meeting> entityList);

    Pme00Meeting register(Pme00Meeting entity);

    void remove(int id);


    List<Pme00Meeting> findAllByAssetId(
                                int cd_tp_id,
                                String title,
                                String startTime,
                                String endTime,
                                String creatorId,
                                String requesterId,
                                String categoryMeeting,
                                String status);

    int findMetingByStartAndEnd(int cdTpId, Date startDate, Date endDate);
}
