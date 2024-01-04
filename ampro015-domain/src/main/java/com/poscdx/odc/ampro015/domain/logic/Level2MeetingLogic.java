package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2MeetingService;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Implements Level2MeetingService
 * @return Pme00AllMeetingResponse
 * @author 202261_Son
 *  @since 2023-11-11
 */
public class Level2MeetingLogic implements Level2MeetingService {
    /**
     * Add Meeting Booking Room function
     * Param newMeeting
     * @return Pme00MeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting){

        Pme00MeetingResponse responseEntity = new Pme00MeetingResponse();
        // validate timespace meeting room
        int count1 = serviceLifecycle.requestPme00MeetingService()
                .findMetingByStartAndEnd(newMeeting.getCd_tp_id() ,newMeeting.getStartTime(),newMeeting.getEndTime());
        //validate meetingId in tb_moo_codes020
//        List<M00Codes020> m00Codes020s = serviceLifecycle.requestM00Codes020Service().findAll();
        final int CD_TP_ID = 65;
        List<M00Codes030> pme00Rooms = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(CD_TP_ID);

        int flagValidateInput = 0;
        int checkMeetingIdOfM00Codes030 = newMeeting.getCd_tp_id();
        String checkCdvOfM00Codes030 = newMeeting.getCdv();
        String checkCdvMeaningOfM00Codes030 = newMeeting.getCdvMeaning();
        for(int i=0; i<pme00Rooms.size(); i++){
            boolean validateInput = (pme00Rooms.get(i).getCdTpId()==checkMeetingIdOfM00Codes030)
                    && (Objects.equals(pme00Rooms.get(i).getCdV(), checkCdvOfM00Codes030))
                    && (Objects.equals(pme00Rooms.get(i).getCdvMeaning(), checkCdvMeaningOfM00Codes030));
                if(validateInput){
                    flagValidateInput= flagValidateInput+1;
                }
        }
        //validate startTime and endtime
        Date dateNow = java.util.Calendar.getInstance().getTime();
        boolean checkDateInput = (newMeeting.getStartTime().compareTo(newMeeting.getEndTime()))<0
                && (newMeeting.getStartTime().compareTo(dateNow)<0);

        if(flagValidateInput>0&&checkDateInput) {
            if (count1 == 0) {
                try {
                    Pme00Meeting pme00Meeting = serviceLifecycle.requestPme00MeetingService().register(newMeeting);
                    responseEntity.setStatus(HttpStatus.OK.value());
                    responseEntity.setMessage("The meeting has been created successfully");
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
                    responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
                    responseEntity.setMessage("This meeting has been created");
                }
            } else {
                responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
                responseEntity.setMessage("Timespace not match");
            }
        }else {
            responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
            responseEntity.setMessage("This room or date input not match");
        }
        return responseEntity;
    }

    /**
     * Get Meeting Booking Room function
     * @Param meetingId
     * @return Pme00MeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int meetingId) {
        Pme00MeetingResponse responseEntity = new Pme00MeetingResponse();
        Pme00Meeting findMeeting = serviceLifecycle.requestPme00MeetingService().find(meetingId);
        List<Pme00EmployeeMeeting> listMember = serviceLifecycle.requestPme00EmployeeMeetingService()
                .findByMeetingId(meetingId);
        if(findMeeting == null) {
            responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
            responseEntity.setData(null);
            responseEntity.setMessage("This meeting room could not be found");
        } else {
            responseEntity.setStatus(HttpStatus.OK.value());
            findMeeting.setListMember(listMember);
            responseEntity.setData(findMeeting);
            responseEntity.setMessage("find successfully");
        }
        return responseEntity;
    }

    /**
     * Delete Meeting Booking Room function
     * @Param meetingId
     * @return Pme00MeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00MeetingResponse deleteMeeting(ServiceLifecycle serviceLifecycle, int meetingId){

        Pme00MeetingResponse responseEntity = new Pme00MeetingResponse();

        Pme00Meeting findMeeting = serviceLifecycle.requestPme00MeetingService().find(meetingId);
        if(findMeeting == null){
            responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
            responseEntity.setMessage("This meeting room could not be found");
        }else{
            List<Pme00EmployeeMeeting> pme00EmployeeMeetings = serviceLifecycle
                    .requestPme00EmployeeMeetingService().findAll();
            for(Pme00EmployeeMeeting pme00EmployeeMeeting : pme00EmployeeMeetings){
                if(pme00EmployeeMeeting.getMeetingId()==meetingId){
                    serviceLifecycle.requestPme00EmployeeMeetingService().deleteAllByMeetingId(meetingId);
                }
            }
            serviceLifecycle.requestPme00MeetingService().remove(meetingId);
            responseEntity.setMessage("Delete meeting room successfully");
        }
        return responseEntity;
    }

    /**
     * Edit List Meeting Booking Room function
     * @Param listMeeting
     * @return Pme00AllMeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00MeetingResponse editMeetingRoom(ServiceLifecycle serviceLifecycle, List<Pme00Meeting> listMeeting){

        Pme00MeetingResponse responseEntity = new Pme00MeetingResponse();

        int meetingId = listMeeting.get(0).getMeetingId();
        Pme00MeetingResponse findMeeting = serviceLifecycle.requestBookingMeetingRoomService()
                .getInforBookingRoom(serviceLifecycle,meetingId);
        if(findMeeting==null) {
            responseEntity.setStatus(HttpStatus.NOT_FOUND.value());
            responseEntity.setMessage("This meeting room could not be found");
        } else {
            responseEntity.setStatus(HttpStatus.OK.value());
            List<Pme00EmployeeMeeting> editEmpMeets = listMeeting.get(0).getListMember();
            List<Pme00EmployeeMeeting> checkEmpMeets = serviceLifecycle.requestPme00EmployeeMeetingService()
                    .findByMeetingId(meetingId);
            for(int j=0; j<checkEmpMeets.size(); j++){
                if(meetingId == checkEmpMeets.get(j).getMeetingId()){
                    int empMeetingId = checkEmpMeets.get(j).getEmpMeetId();
                    serviceLifecycle.requestPme00EmployeeMeetingService().remove(empMeetingId);
                }
            }
            for(int i = 0; i<editEmpMeets.size(); i++){
                  editEmpMeets.get(i).setEmpId(listMeeting.get(0).getListMember().get(i).getEmpId());
                  editEmpMeets.get(i).setEmpName(listMeeting.get(0).getListMember().get(i).getEmpName());

            }
            serviceLifecycle.requestPme00EmployeeMeetingService().modify(editEmpMeets);
            serviceLifecycle.requestPme00MeetingService().modify(listMeeting);
            responseEntity.setData(listMeeting.get(0));
            responseEntity.setMessage("Edit meeting room successfully");
        }
        return responseEntity;
    }

    /**
     * Get List Meeting Booking Room function
     * @return Pme00AllMeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00AllMeetingResponse getListMeeting(ServiceLifecycle serviceLifecycle){
        Pme00AllMeetingResponse responseEntitys = new Pme00AllMeetingResponse();
        List<Pme00Meeting> pme00MeetingList= serviceLifecycle.requestPme00MeetingService().findAll();
        for(int i=0;i< pme00MeetingList.size();i++){
            int meetingId = pme00MeetingList.get(i).getMeetingId();
            pme00MeetingList.get(i).setListMember(serviceLifecycle.requestPme00EmployeeMeetingService()
                    .findByMeetingId(meetingId));
            pme00MeetingList.get(i).setEmpNameList(pme00MeetingList.get(i).getListMember().stream()
                                                                          .map(Pme00EmployeeMeeting::getEmpName)
                                                                          .collect(Collectors.toList()));

        }
        responseEntitys.setStatus(HttpStatus.OK.value());
        responseEntitys.setListData(pme00MeetingList);
        responseEntitys.setMessage("Get all meeting successfully");
        return responseEntitys;
    }

    /**
     * Get List  Room function
     * @return Pme00AllMeetingResponse
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00AllRoomResponse getListRoom(ServiceLifecycle serviceLifecycle){

        Pme00AllRoomResponse responseEntitys = new Pme00AllRoomResponse();

        final int CD_TP_ID = 65;
        List<M00Codes030> pme00Rooms = serviceLifecycle.requestM00Codes030Service().findM00Codes030ById(CD_TP_ID);
        List<Pme00Room> roomList = new ArrayList<>();
        for(int i=0; i<pme00Rooms.size(); i++){
            Pme00Room pme00Room = new Pme00Room();
            pme00Room.setCdTpId(pme00Rooms.get(i).getCdTpId());
            pme00Room.setCdv(pme00Rooms.get(i).getCdV());
            pme00Room.setCdvMeaning(pme00Rooms.get(i).getCdvMeaning());
            roomList.add(pme00Room);
        }
        responseEntitys.setStatus(HttpStatus.OK.value());
        responseEntitys.setData(roomList);
        responseEntitys.setMessage("Get all room successfully");
        return responseEntitys;
    }

    @Override
    public Pme00AllMeetingResponse getMeetingByEndDate(ServiceLifecycle serviceLifecycle) {
        Pme00AllMeetingResponse result = new Pme00AllMeetingResponse();
        List<Pme00Meeting> pme00MeetingList= serviceLifecycle.requestPme00MeetingService().findByEndDate();
        for (Pme00Meeting pme00Meeting : pme00MeetingList) {
            int meetingId = pme00Meeting.getMeetingId();
            pme00Meeting.setListMember(serviceLifecycle.requestPme00EmployeeMeetingService()
                    .findByMeetingId(meetingId));
        }
        result.setStatus(HttpStatus.OK.value());
        result.setListData(pme00MeetingList);
        result.setMessage("Get meetings successfully");
        return result;
    }
}
