package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

add comment info
public interface Level2MeetingService {
    add comment info
    Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting);
    add comment info
    Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int meetingId);
    add comment info
    Pme00MeetingResponse deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId);
    add comment info
    Pme00MeetingResponse editMeetingRoom(ServiceLifecycle serviceLifecycle, List<Pme00Meeting> listMeeting);
    add comment info
    Pme00AllMeetingResponse getListMeeting(ServiceLifecycle serviceLifecycle);

}
