package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.SearchMeetingDto;
import com.poscdx.odc.ampro015.domain.spec.Pme00MeetingService;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class Pme00MeetingResource {
    private final ServiceLifecycler serviceLifecycle;

    @GetMapping("/listMeeting")
    public List<Pme00Meeting> getListMeeting() {
        return this.serviceLifecycle.requestPme00MeetingService().findAll();
    }

    @DeleteMapping("/deleteMeeting")
    public void deleteMeeting(@RequestParam("meetingId") int meetingId) {
        this.serviceLifecycle.requestPme00MeetingService().remove(meetingId);
    }

    @PostMapping("/addMeeting")
    public Pme00MeetingResponse addMeeting(@RequestBody Pme00Meeting newMeeting) {
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        boolean checkValidRequest = newMeeting.getMeetingId() > 0 && newMeeting.getCd_tp_id() > 0 && newMeeting.getCreatorId() != "" && newMeeting.getRequesterId() != "" && newMeeting.getCategoryMeeting() != "" ;
        if( checkValidRequest ) {
            int id = newMeeting.getMeetingId();
            Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
            if (findMeeting == null) {
                this.serviceLifecycle.requestPme00MeetingService().register(newMeeting);
                result.setStatus(HttpStatus.NOT_FOUND.value());
                result.setMessage("The meeting has been created successfully");
            } else {
                result.setStatus(HttpStatus.NOT_FOUND.value());
                result.setMessage("This meeting has been created");
            }
        } else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Not valid request");
        }
        return result;
    }

    @GetMapping("/meetings/{id}")
    public Pme00MeetingResponse getInforBookingRoom(@PathVariable int id) {
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
        if(findMeeting == null) {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setData(null);
            result.setMessage("This meeting room could not be found");
        } else {
        result.setStatus(HttpStatus.OK.value());
        result.setData(findMeeting);
        result.setMessage("find successfully");
        }
        return result;
    }

    @PostMapping("/meetings")
    public List<Pme00Meeting> searchListMeetings(@RequestBody SearchMeetingDto searchMeeting) {

    }

}
