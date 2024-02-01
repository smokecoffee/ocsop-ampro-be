package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllRoomResponse;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import com.poscodx.odc.ampro015.service.PermissionValidation;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Router API for meeting booking Class
 *
 * @author 202261_Son
 * @since 2023-11-11
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class Pme00MeetingResource {

    @Autowired
    private final ServiceLifecycle serviceLifecycle;

    /**
     * Get List Meeting Booking Room function
     * @return Pme00AllMeetingResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('GET_MEETING')")
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
    @PreAuthorize("hasAnyAuthority('DELETE_MEETING,DELETE_MEETING_OWNER')")
    public Pme00MeetingResponse deleteMeeting(@PathVariable("meetingId") int meetingId) {
        Pme00Meeting pme00Meeting = serviceLifecycle.requestPme00MeetingService().find(meetingId);
        if (!PermissionValidation.validateDeleteMeeting(pme00Meeting)) {
            return new Pme00MeetingResponse(HttpStatus.FORBIDDEN.value(), null, Utils.NO_PERMISSION);
        }
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
    @PreAuthorize("hasAnyAuthority('ADD_MEETING')")
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
    @PreAuthorize("hasAnyAuthority('GET_MEETING')")
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
    @PreAuthorize("hasAnyAuthority('UPDATE_MEETING,UPDATE_MEETING_OWNER')")
    public Pme00MeetingResponse editMeetingRoom(@RequestBody List<Pme00Meeting> listMeeting) {
        if (!PermissionValidation.validateUpdateMeeting(listMeeting)) {
            return new Pme00MeetingResponse(HttpStatus.FORBIDDEN.value(), null, Utils.NO_PERMISSION);
        }
        return this.serviceLifecycle.requestBookingMeetingRoomService().editMeetingRoom(serviceLifecycle,listMeeting);
    }
    /**
     * Get List Room function
     * @return Pme00AllRoomResponse
     * @author 202261_Son
     * @since 2023-11-11
     */
    @GetMapping("/findAllRoom")
    @PreAuthorize("hasAnyAuthority('GET_MEETING')")
    public Pme00AllRoomResponse getListRoom(){
        return this.serviceLifecycle.requestBookingMeetingRoomService().getListRoom(serviceLifecycle);
    }

    @GetMapping("/getMeetingRoomByEndDate")
    @PreAuthorize("hasAnyAuthority('GET_MEETING')")
    public Pme00AllMeetingResponse getMeetingRoomByEndDate(){
        return this.serviceLifecycle.requestBookingMeetingRoomService().getMeetingByEndDate(serviceLifecycle);
    }

}
