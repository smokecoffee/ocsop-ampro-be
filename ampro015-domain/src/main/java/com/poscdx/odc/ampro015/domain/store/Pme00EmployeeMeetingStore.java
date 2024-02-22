package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;

import java.util.List;

public interface Pme00EmployeeMeetingStore {
    Pme00EmployeeMeeting retrieve(int id);

    List<Pme00EmployeeMeeting> retrieveAll();

    Pme00EmployeeMeeting update(Pme00EmployeeMeeting Pme00EmployeeMeeting);

    Pme00EmployeeMeeting create(Pme00EmployeeMeeting entity);

    void delete(int id);

    List<Pme00EmployeeMeeting> findByMeetingId(int id);

    void deleteAllByMeetingId(int meetingId);

}