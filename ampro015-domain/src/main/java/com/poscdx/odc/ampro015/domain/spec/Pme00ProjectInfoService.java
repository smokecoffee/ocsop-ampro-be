package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;

import java.util.List;

public interface Pme00ProjectInfoService {
    Pme00ProjectInfo find(String cdVId);

    List<Pme00ProjectInfo> findAll();

    Pme00ProjectInfo modify(Pme00ProjectInfo pme00ProjectInfo);

    void remove(String cdVId);

}
