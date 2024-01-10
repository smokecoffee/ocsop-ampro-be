package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllRoomResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 *Router API for meeting booking Class
 *
 * @author 202261_Son
 * @since 2023-11-11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class Pme00MeetingResource {

    @Autowired
    private final ServiceLifecycler serviceLifecycle;

    /**
     * Get List Meeting Booking Room function
     * @return Pme00AllMeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @GetMapping("/")
    @CrossOrigin
    public Pme00AllMeetingResponse getListMeeting() {
        return this.serviceLifecycle.requestBookingMeetingRoomService().getListMeeting(serviceLifecycle);
    }

    /**
     * Delete Meeting Booking Room By Id function
     * @Param meetingId
     * @return Pme00MeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @DeleteMapping("/{meetingId}")
    @CrossOrigin
    public Pme00MeetingResponse deleteMeeting(@PathVariable("meetingId") int meetingId) {
        return this.serviceLifecycle.requestBookingMeetingRoomService().deleteMeeting(serviceLifecycle, meetingId);
    }

    /**
     * Add Meeting Booking Room By Id function
     * Param newMeeting
     * @return Pme00MeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @PostMapping("/")
    @CrossOrigin
    public Pme00MeetingResponse addMeeting(@RequestBody Pme00Meeting newMeeting) throws ParseException {
       return this.serviceLifecycle.requestBookingMeetingRoomService().addMeeting(serviceLifecycle, newMeeting );
    }

    /**
     * Get Meeting Booking Room By Id function
     * @Param meetingId
     * @return Pme00MeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @GetMapping("/{meetingId}")
    @CrossOrigin
    public Pme00MeetingResponse getInfoBookingRoom(@PathVariable int meetingId) {
       return this.serviceLifecycle.requestBookingMeetingRoomService().getInfoBookingRoom(serviceLifecycle,meetingId);
    }


    /**
     * Edit Meeting Booking Room By Id function
     * @Param listMeeting
     * @return Pme00MeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @PutMapping("/")
    @CrossOrigin
    public Pme00MeetingResponse editMeetingRoom(@RequestBody List<Pme00Meeting> listMeeting) {
        return this.serviceLifecycle.requestBookingMeetingRoomService().editMeetingRoom(serviceLifecycle,listMeeting);
    }
    /**
     * Get List Room function
     * @return Pme00AllRoomResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @GetMapping("/findAllRoom")
    @CrossOrigin
    public Pme00AllRoomResponse getListRoom(){
        return this.serviceLifecycle.requestBookingMeetingRoomService().getListRoom(serviceLifecycle);
    }

    @GetMapping("/getMeetingRoomByEndDate")
    @CrossOrigin
    public Pme00AllMeetingResponse getMeetingRoomByEndDate(){
        return this.serviceLifecycle.requestBookingMeetingRoomService().getMeetingByEndDate(serviceLifecycle);
    }

}
