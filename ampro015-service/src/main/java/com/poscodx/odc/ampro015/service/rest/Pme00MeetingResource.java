package com.poscodx.odc.ampro015.service.rest;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
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
    public ResponseEntity addMeeting(@RequestBody Pme00Meeting newMeeting) {
        boolean checkValidRequest = newMeeting.getMeetingId() > 0 && newMeeting.getCd_tp_id() > 0 && newMeeting.getCreatorId() != "" && newMeeting.getRequesterId() != "" && newMeeting.getCategoryMeeting() != "" ;
        if( checkValidRequest ) {
            int id = newMeeting.getMeetingId();
            Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
            if (findMeeting == null) {
                this.serviceLifecycle.requestPme00MeetingService().register(newMeeting);
                return new ResponseEntity<>("The meeting has been created successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("This meeting has been created", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Not valid request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getInforBookingRoom/{id}")
    public Object searchMeeting(@PathVariable int id) {
        Pme00Meeting findMeeting = this.serviceLifecycle.requestPme00MeetingService().find(id);
        if(findMeeting == null) {
            return new ResponseEntity<>("This meeting room could not be found", HttpStatus.BAD_REQUEST);
        } else {
            return findMeeting;
        }
    }
}
