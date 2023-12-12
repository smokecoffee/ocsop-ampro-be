package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2MeetingService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Level2MeetingLogic implements Level2MeetingService {

    @Override
    public Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting) {
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        boolean checkValidRequest = newMeeting.getCd_tp_id() == 65;
        if (checkValidRequest) {
            try {
                Pme00Meeting pme00Meeting = serviceLifecycle.requestPme00MeetingService().register(newMeeting);
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("The meeting has been created successfully");
                //register EmployeeMeeting
                List<Pme00EmployeeMeeting> listMember = newMeeting.getListMember();

                listMember = listMember.stream().map(i -> {
                            i.setMeetingId(pme00Meeting.getMeetingId());
                            return i;
                        })
                        .collect(Collectors.toList());

                Set<String> setId = listMember.stream()
                        .map(Pme00EmployeeMeeting::getEmpId)
                        .collect(Collectors.toSet());
                System.out.println("setEmpId: " + setId);

                for (Pme00EmployeeMeeting pme00EmployeeMeeting : listMember) {
                    if (setId.contains(pme00EmployeeMeeting.getEmpId())) {
                        serviceLifecycle.requestPme00EmployeeMeetingService()
                                .register(pme00EmployeeMeeting);
                        setId.remove(pme00EmployeeMeeting.getEmpId());
                    }
                }
            } catch (Exception e) {
                result.setStatus(HttpStatus.NOT_FOUND.value());
                result.setMessage("This meeting has been created");
            }
        } else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("Not valid request");
        }
        return result;
    }
    @Override
    public Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int id) {
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        Pme00Meeting findMeeting = serviceLifecycle.requestPme00MeetingService().find(id);
        List<Pme00EmployeeMeeting> listMember = serviceLifecycle.requestPme00EmployeeMeetingService()
                .findByMeetingId(id);
        if(findMeeting == null) {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setData(null);
            result.setMessage("This meeting room could not be found");
        } else {
            result.setStatus(HttpStatus.OK.value());
            findMeeting.setListMember(listMember);
            result.setData(findMeeting);
            result.setMessage("find successfully");
        }
        return result;
    }
@Override
public Pme00MeetingResponse deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId){
    Pme00MeetingResponse result = new Pme00MeetingResponse();
    Pme00Meeting findMeeting = serviceLifecycle.requestPme00MeetingService().find(meetingId);
    if(findMeeting==null){
        result.setStatus(HttpStatus.NOT_FOUND.value());
        result.setMessage("This meeting room could not be found");
    }else{
        List<Pme00EmployeeMeeting> pme00EmployeeMeetings = serviceLifecycle.requestPme00EmployeeMeetingService().findAll();
        for(Pme00EmployeeMeeting pme00EmployeeMeeting : pme00EmployeeMeetings){
            if(pme00EmployeeMeeting.getMeetingId() == meetingId){
                serviceLifecycle.requestPme00EmployeeMeetingService().deleteAllByMeetingId(meetingId);
            }
        }
        serviceLifecycle.requestPme00MeetingService().remove(meetingId);
        result.setMessage("Delete meeting room successfully");
    }
    return result;
}

@Override
public Pme00MeetingResponse editMeetingRoom(ServiceLifecycle serviceLifecycle, List<Pme00Meeting> listMeeting){
    int id = listMeeting.get(0).getMeetingId();
    Pme00MeetingResponse result = new Pme00MeetingResponse();
    Pme00MeetingResponse findMeeting = serviceLifecycle.bookingMeetingRoomService().getInforBookingRoom(serviceLifecycle,id);
    if(findMeeting == null) {
        result.setStatus(HttpStatus.NOT_FOUND.value());
        result.setMessage("This meeting room could not be found");
    } else {
        result.setStatus(HttpStatus.OK.value());
        List<Pme00EmployeeMeeting> editEmpMeets = listMeeting.get(0).getListMember();
        for(int i = 0; i<editEmpMeets.size(); i++){
            editEmpMeets.get(i).setEmpId(listMeeting.get(0).getListMember().get(i).getEmpId());
            editEmpMeets.get(i).setEmpName(listMeeting.get(0).getListMember().get(i).getEmpName());
        }
        serviceLifecycle.requestPme00EmployeeMeetingService().modify(editEmpMeets);
        serviceLifecycle.requestPme00MeetingService().modify(listMeeting);
        result.setData(listMeeting.get(0));
        result.setMessage("Edit meeting room successfully");
    }
    return result;
}

}
