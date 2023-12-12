package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

public interface Level2MeetingService {

    Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting);

    Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int id);

    void deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId);
}
