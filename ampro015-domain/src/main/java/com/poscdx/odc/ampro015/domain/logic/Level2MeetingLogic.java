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
        int count1 = serviceLifecycle.requestPme00MeetingService()
                .findMetingByStartAndEnd(newMeeting.getStartTime(), newMeeting.getEndTime());
        Pme00AllMeetingResponse pme00AllMeetingResponse = serviceLifecycle.bookingMeetingRoomService()
                .getListMeeting(serviceLifecycle);
        List<Pme00Meeting> pme00MeetingList = pme00AllMeetingResponse.getListData();
        int flagCheckMeetingId=0;
        for(int i=0; i<pme00MeetingList.size(); i++){
            if(newMeeting.getMeetingId() == pme00MeetingList.get(i).getMeetingId()){
                flagCheckMeetingId = flagCheckMeetingId+1;
            }
        }
        if(flagCheckMeetingId==0){
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
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("This meeting is exist!!!");
        }
        if (flagCheckMeetingId>0) {
            if (count1 == 0) {
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
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("This meeting is exist!!!");
            }
        }
        //get CheckDateMeeting(StartDate and EndDate) match with Date input
        //viet ham dung chung truyen tham so
//        Pme00AllMeetingResponse pme00AllMeetingResponse = serviceLifecycle.bookingMeetingRoomService()
//                .getListMeeting(serviceLifecycle);
//        List<Pme00Meeting> pme00MeetingList = pme00AllMeetingResponse.getListData();
//        List<CheckOnlyDateBookMeeting> listOnlyDateCheck = new ArrayList<>();
//        Date startDateInputCheck = newMeeting.getStartTime();
//
//        for(int i=0; i<pme00MeetingList.size(); i++){
//            CheckOnlyDateBookMeeting checkOnlyDateBookMeeting = new CheckOnlyDateBookMeeting();
//            Date startDateCheck = pme00MeetingList.get(i).getStartTime();
//            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            String strDateCheck = dateFormat.format(startDateCheck);
//            String strDateInputCheck = dateFormat.format(startDateInputCheck);
//            if(strDateCheck.equals(strDateInputCheck)) {
//                checkOnlyDateBookMeeting.setStartOnlyDate(pme00MeetingList.get(i).getStartTime());
//                checkOnlyDateBookMeeting.setEndOnlyDate(pme00MeetingList.get(i).getEndTime());
//                listOnlyDateCheck.add(checkOnlyDateBookMeeting);
//                System.out.println(": pme00MeetingList" + checkOnlyDateBookMeeting);
//            }
//        }
//        // sort by StartDate and EndDate
//        listOnlyDateCheck.sort(Comparator.comparing(CheckOnlyDateBookMeeting::getStartOnlyDate));
//        System.out.println("listDatecheck" + listOnlyDateCheck);
//
//        List<ListTimeCheckMeeting> listTimeCheckMeetings = new ArrayList<>();
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
////        SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyy-MM-DD HH:mm:ss");
//        Date startTimeCheckInput = newMeeting.getStartTime();
//
//        //create list space time: validateTimeCheckMeetings
//        int listSpaceSize = listOnlyDateCheck.size();
//        for(int i=0; i <listSpaceSize; i++){
//            String strStartTimeCheckInput = dateFormat1.format(startTimeCheckInput);
//            String strtoDateS = strStartTimeCheckInput + " 00:00:00.000";
//            String strtoDateE = strStartTimeCheckInput + " 23:59:00.000";
//            Date startDayTime = dateFormat2.parse(strtoDateS);
//            Date endDayTime = dateFormat2.parse(strtoDateE);
//            ListTimeCheckMeeting listTimeCheckMeeting = new ListTimeCheckMeeting();
//            if(i==0){
//                if(!startDayTime.equals(listOnlyDateCheck.get(i).getStartOnlyDate())){
//                    //add first
//                    listTimeCheckMeeting.setStartTimeCheck(startDayTime);
//                    listTimeCheckMeeting.setEndTimeCheck(listOnlyDateCheck.get(i).getStartOnlyDate());
//                    listTimeCheckMeetings.add(listTimeCheckMeeting);
//
//                    if(listSpaceSize == 1) {
//                        //add second
//                        listTimeCheckMeeting.setStartTimeCheck(listOnlyDateCheck.get(i).getEndOnlyDate());
//                        listTimeCheckMeeting.setEndTimeCheck(endDayTime);
//                        listTimeCheckMeetings.add(listTimeCheckMeeting);
//                    } else {
//                        //add second
//                        listTimeCheckMeeting.setStartTimeCheck(listOnlyDateCheck.get(i).getEndOnlyDate());
//                        listTimeCheckMeeting.setEndTimeCheck(listOnlyDateCheck.get(i + 1).getStartOnlyDate());
//                        listTimeCheckMeetings.add(listTimeCheckMeeting);
//                    }
//
//                } else {
//                    listTimeCheckMeeting.setStartTimeCheck(listOnlyDateCheck.get(i).getEndOnlyDate());
//                    listTimeCheckMeeting.setEndTimeCheck(listOnlyDateCheck.get(i + 1).getStartOnlyDate());
//                    listTimeCheckMeetings.add(listTimeCheckMeeting);
//                }
//            } else {
//                if(i==listSpaceSize-1){
//                    listTimeCheckMeeting.setStartTimeCheck(listOnlyDateCheck.get(i).getEndOnlyDate());
//                    listTimeCheckMeeting.setEndTimeCheck(endDayTime);
//                    listTimeCheckMeetings.add(listTimeCheckMeeting);
//                } else if (i<listSpaceSize){
//                    listTimeCheckMeeting.setStartTimeCheck(listOnlyDateCheck.get(i).getEndOnlyDate());
//                    listTimeCheckMeeting.setEndTimeCheck(listOnlyDateCheck.get(i + 1).getStartOnlyDate());
//                    listTimeCheckMeetings.add(listTimeCheckMeeting);
//                } else {
//                    //do nothing
//                }
//            }
//        }
//        System.out.println("validateTimeCheckMeetings: " + listTimeCheckMeetings);
//
//        //check time booking in timespace
//        Date startTimeCheckInputCheckTimeSpaceStart = newMeeting.getStartTime();
//        Date startTimeCheckInputCheckTimeSpaceEnd = newMeeting.getEndTime();
//        int flagCheckTimeSpace=0;
//        for(int i=0; i<listTimeCheckMeetings.size(); i++){
//           boolean checkTimeSpace=(startTimeCheckInputCheckTimeSpaceStart.compareTo(listTimeCheckMeetings.get(i)
//                   .getStartTimeCheck())>0)
//                    && (startTimeCheckInputCheckTimeSpaceEnd.compareTo(listTimeCheckMeetings.get(i)
//                   .getEndTimeCheck())<0);
//           if(checkTimeSpace){
//               flagCheckTimeSpace = flagCheckTimeSpace+1;
//           }
//        }
//        //validate Input
//        //add Api get MeetingRoom
//        //validate MeetingRoomId
//        Date dateNow=java.util.Calendar.getInstance().getTime();
//        List<M00Codes020> m00Codes020s = serviceLifecycle.m00Codes020Service().findAll();
//        int flagCheckMeetingId=0;
//        int checkMeetingId = newMeeting.getCd_tp_id();
//        for(int i=0; i<m00Codes020s.size(); i++){
//            if("MEETING_ROOM".equals(m00Codes020s.get(i).getCdTp())){
//                if(checkMeetingId==m00Codes020s.get(i).getCdTpId()){
//                    flagCheckMeetingId = flagCheckMeetingId+1;
//                }
//            }
//        }
//        //validate Input newMeeting Room
//        boolean checkValidRequest = flagCheckMeetingId>0 && (newMeeting.getStartTime()
//                .compareTo(newMeeting.getEndTime()) < 0) && (newMeeting.getEndTime().compareTo(dateNow)<0)
//                && (flagCheckTimeSpace>0||listOnlyDateCheck.isEmpty());
//
//        if (checkValidRequest) {
//            try {
//
//                Pme00Meeting pme00Meeting = serviceLifecycle.requestPme00MeetingService().register(newMeeting);
//                result.setStatus(HttpStatus.OK.value());
//                result.setMessage("The meeting has been created successfully");
//                //register EmployeeMeeting
//                List<Pme00EmployeeMeeting> listMember = newMeeting.getListMember();
//
//                listMember = listMember.stream().map(i -> {
//                            i.setMeetingId(pme00Meeting.getMeetingId());
//                            return i;
//                        })
//                        .collect(Collectors.toList());
//
//                Set<String> setId = listMember.stream()
//                        .map(Pme00EmployeeMeeting::getEmpId)
//                        .collect(Collectors.toSet());
//                System.out.println("setEmpId: " + setId);
//
//                for (Pme00EmployeeMeeting pme00EmployeeMeeting : listMember) {
//                    if (setId.contains(pme00EmployeeMeeting.getEmpId())) {
//                        serviceLifecycle.requestPme00EmployeeMeetingService()
//                                .register(pme00EmployeeMeeting);
//                        setId.remove(pme00EmployeeMeeting.getEmpId());
//                    }
//                }
//            } catch (Exception e) {
//                result.setStatus(HttpStatus.NOT_FOUND.value());
//                result.setMessage("This meeting has been created");
//            }
//        } else {
//            result.setStatus(HttpStatus.NOT_FOUND.value());
//            result.setMessage("Not valid request");
//        }
        return result;
    }
    @Override
    public Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int meetingId) {
        Pme00MeetingResponse result = new Pme00MeetingResponse();
        Pme00Meeting findMeeting = serviceLifecycle.requestPme00MeetingService().find(meetingId);
        List<Pme00EmployeeMeeting> listMember = serviceLifecycle.requestPme00EmployeeMeetingService()
                .findByMeetingId(meetingId);
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
