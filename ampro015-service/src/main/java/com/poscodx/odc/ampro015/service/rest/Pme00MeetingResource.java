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

import java.util.Date;
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
        if(checkValidRequest) {
            int id = newMeeting.getMeetingId();
            Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
            if (findMeeting == null) {
                this.serviceLifecycle.requestPme00MeetingService().register(newMeeting);
                result.setStatus(HttpStatus.OK.value());
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

    @GetMapping("/meetings")
    public List<Pme00Meeting> searchListMeetings(
            @RequestParam("cd_tp_id") int cd_tp_id,
            @RequestParam("title") String title,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("creatorId") String creatorId,
            @RequestParam("requesterId") String  requesterId,
            @RequestParam("categoryMeeting") String categoryMeeting,
            @RequestParam("status") String status

            ) {
       return this.serviceLifecycle.requestPme00MeetingService().findAllByAssetId(cd_tp_id, title, startTime, endTime, creatorId, requesterId, categoryMeeting, status);
    }

    @PutMapping("/editMeeting")
    public Pme00MeetingResponse editMeetingRoom(@RequestBody Pme00Meeting newMeeting) {
        int id = newMeeting.getMeetingId();
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
        if(findMeeting == null) {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("This meeting room could not be found");
        } else {
            result.setStatus(HttpStatus.OK.value());
            result.setData(newMeeting);
            result.setMessage("Edit meeting room successfully");
        }
        return result;
    }
}
