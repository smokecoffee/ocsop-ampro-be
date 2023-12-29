package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllRoomResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;

/**
 * Interface Level2MeetingService
 * @author 202261_Son
 *  @since 2023-11-11
 */
public interface Level2MeetingService {
    /**
     * Add List Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting);
    /**
     * Get Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int meetingId);
    /**
     * Delete Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00MeetingResponse deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId);
    /**
     * Edit List Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00MeetingResponse editMeetingRoom(ServiceLifecycle serviceLifecycle, List<Pme00Meeting> listMeeting);
    /**
     * Get Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00AllMeetingResponse getListMeeting(ServiceLifecycle serviceLifecycle);

    Pme00AllRoomResponse getListRoom(ServiceLifecycle serviceLifecycle);

}
