package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;

import java.util.List;

public interface Pme00EmployeeMeetingService {

    Pme00EmployeeMeeting find(int id);

    List<Pme00EmployeeMeeting> findAll();

    void modify(List<Pme00EmployeeMeeting> entityList);

    Pme00EmployeeMeeting register(Pme00EmployeeMeeting entity);

    void remove(int id);

    List<Pme00EmployeeMeeting> findByMeetingId(int id);

    void deleteAllByMeetingId(int meetingId);
}