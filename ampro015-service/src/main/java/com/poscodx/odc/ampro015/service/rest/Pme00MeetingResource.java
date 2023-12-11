package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscodx.odc.ampro015.service.lifecycle.ServiceLifecycler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class Pme00MeetingResource {

    private final ServiceLifecycler serviceLifecycle;

    public List<Pme00Meeting> getListMeeting() {
        return this.serviceLifecycle.requestPme00MeetingService().findAll();
    }

    @DeleteMapping("/{meetingId}")
    public void deleteMeeting(@PathVariable("meetingId") int meetingId) {
        List<Pme00EmployeeMeeting> pme00EmployeeMeetings = serviceLifecycle.requestPme00EmployeeMeetingService().findAll();
        for(Pme00EmployeeMeeting pme00EmployeeMeeting : pme00EmployeeMeetings){
            if(pme00EmployeeMeeting.getMeetingId() == meetingId){
                this.serviceLifecycle.requestPme00EmployeeMeetingService().deleteAllByMeetingId(meetingId);
            }
        }
        this.serviceLifecycle.requestPme00MeetingService().remove(meetingId);
    }

    @PostMapping("/")
    public Pme00MeetingResponse addMeeting(@RequestBody Pme00Meeting newMeeting) {
       return this.serviceLifecycle.requestLevel2Service().addMeeting(serviceLifecycle, newMeeting);
    }

    @GetMapping("/{id}")
    public Pme00MeetingResponse getInforBookingRoom(@PathVariable int id) {
       return this.serviceLifecycle.requestLevel2Service().getInforBookingRoom(serviceLifecycle,id);
    }

    @GetMapping("/search")
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
