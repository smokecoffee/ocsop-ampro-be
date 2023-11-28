package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeetingId;

import java.util.List;

public interface Pme00EmployeeMeetingStore {
    Pme00EmployeeMeeting retrieve(Pme00EmployeeMeetingId entityId);

    List<Pme00EmployeeMeeting> retrieveAll();

    Pme00EmployeeMeeting update(Pme00EmployeeMeeting Pme00EmployeeMeeting);

    Pme00EmployeeMeeting create(Pme00EmployeeMeeting entity);

    void delete(Pme00EmployeeMeetingId entityId);

}
