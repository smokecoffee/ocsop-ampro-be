package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class Pme00MeetingResource {

    private final ServiceLifecycler serviceLifecycle;

    @GetMapping("/")
    @CrossOrigin
    public Pme00AllMeetingResponse getListMeeting() {
        return this.serviceLifecycle.bookingMeetingRoomService().getListMeeting(serviceLifecycle);
    }

    @DeleteMapping("/{meetingId}")
    @CrossOrigin
    public Pme00MeetingResponse deleteMeeting(@PathVariable("meetingId") int meetingId) {
        return this.serviceLifecycle.bookingMeetingRoomService().deleteMeeting(serviceLifecycle, meetingId);
    }

    @PostMapping("/")
    @CrossOrigin
    public Pme00MeetingResponse addMeeting(@RequestBody Pme00Meeting newMeeting) throws ParseException {
       return this.serviceLifecycle.bookingMeetingRoomService().addMeeting(serviceLifecycle, newMeeting);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Pme00MeetingResponse getInforBookingRoom(@PathVariable int id) {
       return this.serviceLifecycle.bookingMeetingRoomService().getInforBookingRoom(serviceLifecycle,id);
    }

    @GetMapping("/search")
    @CrossOrigin
    public List<Pme00Meeting> searchListMeetings(
            @RequestParam("cd_tp_id") int cd_tp_id,
            @RequestParam("title") String title,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("creatorId") String creatorId,
            @RequestParam("requesterId") String  requesterId,
            @RequestParam("categoryMeeting") String categoryMeeting,
            @RequestParam("status") String status) {
       return this.serviceLifecycle.requestPme00MeetingService()
               .findAllByAssetId(cd_tp_id,
                                title,
                                startTime,
                                endTime,
                                creatorId,
                                requesterId,
                                categoryMeeting,
                                status);
    }

    @PutMapping("/")
    @CrossOrigin
    public Pme00MeetingResponse editMeetingRoom(@RequestBody List<Pme00Meeting> listMeeting) {
        return this.serviceLifecycle.bookingMeetingRoomService().editMeetingRoom(serviceLifecycle,listMeeting);
    }
}
