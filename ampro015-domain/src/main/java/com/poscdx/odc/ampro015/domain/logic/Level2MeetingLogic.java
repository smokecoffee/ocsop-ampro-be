package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2MeetingService;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Level2MeetingLogic implements Level2MeetingService {

    @Override
    public Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting)
            throws ParseException {

        Pme00MeetingResponse result = new Pme00MeetingResponse();
        //get CheckDateMeeting(StartDate and EndDate) match with input
        Pme00AllMeetingResponse pme00AllMeetingResponse = serviceLifecycle.bookingMeetingRoomService()
                .getListMeeting(serviceLifecycle);
        List<Pme00Meeting> pme00MeetingList = pme00AllMeetingResponse.getListData();
        List<CheckDateBookMeeting> listDatecheck = new ArrayList<>();
        Date startDateInputCheck = newMeeting.getStartTime();

        for(int i=0; i<pme00MeetingList.size(); i++){
            CheckDateBookMeeting checkDateBookMeeting = new CheckDateBookMeeting();
            Date startDateCheck = pme00MeetingList.get(i).getStartTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String strDateCheck = dateFormat.format(startDateCheck);
            String strDateInputCheck = dateFormat.format(startDateInputCheck);
            if(strDateCheck.equals(strDateInputCheck)) {
                checkDateBookMeeting.setStartTime(pme00MeetingList.get(i).getStartTime());
                checkDateBookMeeting.setEndTime(pme00MeetingList.get(i).getEndTime());
                listDatecheck.add(checkDateBookMeeting);
                System.out.println(": pme00MeetingList" + checkDateBookMeeting);
            }
        }
        // sort by StartDate and EndDate
        listDatecheck.sort(Comparator.comparing(CheckDateBookMeeting::getStartTime));
        System.out.println("listDatecheck" + listDatecheck);

        List<ValidateTimeCheckMeeting> validateTimeCheckMeetings = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//        SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyy-MM-DD HH:mm:ss");
        Date startTimeCheckInput = newMeeting.getStartTime();

        //create list space time: validateTimeCheckMeetings
        int listSpaceSize = listDatecheck.size();
        for(int i=0 ; i < listSpaceSize ; i++){
            String strStartTimeCheckInput = dateFormat1.format(startTimeCheckInput);
            String strtoDateS = strStartTimeCheckInput + " 00:00:00.000";
            String strtoDateE = strStartTimeCheckInput + " 23:59:00.000";
            Date startDayTime = dateFormat2.parse(strtoDateS);
            Date endDayTime = dateFormat2.parse(strtoDateE);
            ValidateTimeCheckMeeting validateTimeCheckMeeting = new ValidateTimeCheckMeeting();
            if(i==0){

                if(!startDayTime.equals(listDatecheck.get(i).getStartTime())){
                    //add first
                    validateTimeCheckMeeting.setStartTimeCheck(startDayTime);
                    validateTimeCheckMeeting.setEndTimeCheck(listDatecheck.get(i).getStartTime());
                    validateTimeCheckMeetings.add(validateTimeCheckMeeting);

                    //add second
                    validateTimeCheckMeeting.setStartTimeCheck(listDatecheck.get(i).getEndTime());
                    validateTimeCheckMeeting.setEndTimeCheck(listDatecheck.get(i + 1).getStartTime());
                    validateTimeCheckMeetings.add(validateTimeCheckMeeting);
                } else {
                    validateTimeCheckMeeting.setStartTimeCheck(listDatecheck.get(i).getEndTime());
                    validateTimeCheckMeeting.setEndTimeCheck(listDatecheck.get(i + 1).getStartTime());
                    validateTimeCheckMeetings.add(validateTimeCheckMeeting);
                }
            } else {
                if(i==listSpaceSize-1){
                    validateTimeCheckMeeting.setStartTimeCheck(listDatecheck.get(i).getEndTime());
                    validateTimeCheckMeeting.setEndTimeCheck(endDayTime);
                    validateTimeCheckMeetings.add(validateTimeCheckMeeting);
                } else if (i<listSpaceSize){
                    validateTimeCheckMeeting.setStartTimeCheck(listDatecheck.get(i).getEndTime());
                    validateTimeCheckMeeting.setEndTimeCheck(listDatecheck.get(i + 1).getStartTime());
                    validateTimeCheckMeetings.add(validateTimeCheckMeeting);
                } else {
                    //do nothing
                }
            }
        }
        System.out.println("validateTimeCheckMeetings: " + validateTimeCheckMeetings);

        //check time booking in timespace
        Date startTimeCheckInputCheckTimeSpaceStart = newMeeting.getStartTime();
        Date startTimeCheckInputCheckTimeSpaceEnd = newMeeting.getEndTime();
        int flag=0;
        for(int i=0; i<validateTimeCheckMeetings.size(); i++){
           boolean checkTimeSpace= (startTimeCheckInputCheckTimeSpaceStart.compareTo(validateTimeCheckMeetings.get(i)
                   .getStartTimeCheck())>0)
                    && (startTimeCheckInputCheckTimeSpaceEnd.compareTo(validateTimeCheckMeetings.get(i)
                   .getEndTimeCheck())<0);
           if(checkTimeSpace){
               flag = flag+1;
           }
        }
        //validate Input
        //add Api get MeetingRoom
        Date date=java.util.Calendar.getInstance().getTime();
        boolean checkValidRequest = newMeeting.getCd_tp_id()==65 && (newMeeting.getStartTime()
                .compareTo(newMeeting.getEndTime()) < 0) && (newMeeting.getEndTime().compareTo(date)<0)
                && flag>0;

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
        if(findMeeting==null) {
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
        List<Pme00EmployeeMeeting> pme00EmployeeMeetings = serviceLifecycle
                .requestPme00EmployeeMeetingService().findAll();
        for(Pme00EmployeeMeeting pme00EmployeeMeeting : pme00EmployeeMeetings){
            if(pme00EmployeeMeeting.getMeetingId()==meetingId){
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
    Pme00MeetingResponse findMeeting = serviceLifecycle.bookingMeetingRoomService()
            .getInforBookingRoom(serviceLifecycle,id);
    if(findMeeting==null) {
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
@Override
public Pme00AllMeetingResponse getListMeeting(ServiceLifecycle serviceLifecycle){
    Pme00AllMeetingResponse result = new Pme00AllMeetingResponse();
    List<Pme00Meeting> pme00MeetingList= serviceLifecycle.requestPme00MeetingService().findAll();
    for(int i=0;i< pme00MeetingList.size();i++){
        int meetingId = pme00MeetingList.get(i).getMeetingId();
        pme00MeetingList.get(i).setListMember(serviceLifecycle.requestPme00EmployeeMeetingService()
                .findByMeetingId(meetingId));
    }
    result.setStatus(HttpStatus.OK.value());
    result.setListData(pme00MeetingList);
    result.setMessage("Get all meeting successfully");
    return result;
};

}
