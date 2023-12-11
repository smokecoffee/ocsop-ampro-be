package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.PathVariable;

public interface BookingMeetingRoomService {

    Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting);

    Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int id);

    void deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId);
}
