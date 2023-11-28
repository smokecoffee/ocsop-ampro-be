package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeetingId;

import java.util.List;

public interface Pme00EmployeeMeetingService {

    Pme00EmployeeMeeting find(Pme00EmployeeMeetingId entityId);

    List<Pme00EmployeeMeeting> findAll();

    void modify(List<Pme00EmployeeMeeting> entityList);

    Pme00EmployeeMeeting register(Pme00EmployeeMeeting entity);

    void remove(Pme00EmployeeMeetingId entityId);

}
