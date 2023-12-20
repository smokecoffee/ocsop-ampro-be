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

        // validate timespace meeting room
        int count1 = serviceLifecycle.requestPme00MeetingService()
                .findMetingByStartAndEnd(newMeeting.getCd_tp_id() ,newMeeting.getStartTime(),newMeeting.getEndTime());
        Pme00AllMeetingResponse pme00AllMeetingResponse = serviceLifecycle.requestBookingMeetingRoomService()
                .getListMeeting(serviceLifecycle);
        // validate timespace meeting room
        //validate meetingId in tb_moo_codes020
        List<M00Codes020> m00Codes020s = serviceLifecycle.requestM00Codes020Service().findAll();
        int flagCheckMeetingIdOfM00Codes020 =0;
        int checkMeetingIdOfM00Codes020 = newMeeting.getCd_tp_id();
        for(int i=0; i<m00Codes020s.size(); i++){
            if("MEETING_ROOM".equals(m00Codes020s.get(i).getCdTp())){
                if(checkMeetingIdOfM00Codes020==m00Codes020s.get(i).getCdTpId()){
                    flagCheckMeetingIdOfM00Codes020= flagCheckMeetingIdOfM00Codes020+1;
                }
            }
        }
        //validate startTime and endtime
        Date dateNow = java.util.Calendar.getInstance().getTime();
        boolean checkDateInput = (newMeeting.getStartTime().compareTo(newMeeting.getEndTime()))<0
                && (newMeeting.getStartTime().compareTo(dateNow)>=0);

        if(flagCheckMeetingIdOfM00Codes020>0&&checkDateInput) {
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
                result.setStatus(HttpStatus.NOT_FOUND.value());
                result.setMessage("Timespace not match");
            }
        }else {
            result.setStatus(HttpStatus.NOT_FOUND.value());
            result.setMessage("This room or date input not match");
        }
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
        Pme00MeetingResponse findMeeting = serviceLifecycle.requestBookingMeetingRoomService()
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
    }

    @Override
    public Pme00AllMeetingResponse findMeetingRoomByEndDate(ServiceLifecycle serviceLifecycle) throws ParseException {
        Pme00AllMeetingResponse result = new Pme00AllMeetingResponse();
        Date dateNow = java.util.Calendar.getInstance().getTime();
        //format current date to standard
        SimpleDateFormat formatDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String dateToStr = formatDateNow.format(dateNow);
        //convert current date to type String
        SimpleDateFormat dateFormatStrToDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date dateNowToDate = dateFormatStrToDate1.parse(dateToStr);
        //get date now only date, not time
        DateFormat dateFormatDateToStr = new SimpleDateFormat("yyyy-MM-dd");
        String dateToStringOnlyDate = dateFormatDateToStr.format(dateNow);
        //set time finish in current date
        SimpleDateFormat dateFormatStrToDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String strToDate = dateToStringOnlyDate + " 23:59:00.000";
        Date formatStrtoDate = dateFormatStrToDate2.parse(strToDate);

        List<Pme00Meeting> pme00MeetingList= serviceLifecycle.requestPme00MeetingService().findAll();

        //get list meeting by endDate at current date
        for(int i=0;i< pme00MeetingList.size();i++){
            boolean timeCheck = (pme00MeetingList.get(i).getEndTime().compareTo(dateNowToDate)>0)
                    &&(pme00MeetingList.get(i).getEndTime().compareTo(formatStrtoDate)<0);
            if(timeCheck){
            int meetingId = pme00MeetingList.get(i).getMeetingId();
            pme00MeetingList.get(i).setListMember(serviceLifecycle.requestPme00EmployeeMeetingService()
                    .findByMeetingId(meetingId));}
        }

        List<Pme00Meeting> tmp = pme00MeetingList.stream()
                .filter(i -> i.getListMember() != null && !i.getListMember().isEmpty()).collect(Collectors.toList());

        result.setStatus(HttpStatus.OK.value());
        boolean timecheck1 = false;
        for(int j = 0; j<tmp.size(); j++){
            timecheck1 = (tmp.get(j).getEndTime().compareTo(dateNowToDate)>0)
                    &&(tmp.get(j).getEndTime().compareTo(formatStrtoDate)<0);

        }
        if(timecheck1){
            result.setListData(tmp);
        }
        result.setMessage("Get all meeting by endDate successfully");
        return result;
    }
}
