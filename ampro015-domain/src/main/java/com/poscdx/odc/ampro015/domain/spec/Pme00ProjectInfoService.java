package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;

import java.util.List;

public interface Pme00ProjectInfoService {
    Pme00ProjectInfo find(String cdVId);

    List<Pme00ProjectInfo> findAll();

    void modify(List<Pme00ProjectInfo> pme00ProjectInfo);

    Pme00ProjectInfo register(Pme00ProjectInfo entity);

    void remove(String cdVId);

}
