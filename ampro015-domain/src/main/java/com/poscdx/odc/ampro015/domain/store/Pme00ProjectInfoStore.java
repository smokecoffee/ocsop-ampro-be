package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;

import java.util.List;

public interface Pme00ProjectInfoStore {
    Pme00ProjectInfo retrieve(String cdVId);

    List<Pme00ProjectInfo> retrieveAll();

    Pme00ProjectInfo update(Pme00ProjectInfo entity);

    Pme00ProjectInfo create(Pme00ProjectInfo entity);

    void delete (String cdVId);

    List<Object[]> getActiveEmployee();

    List<Object[]> getTaskStatus();
}
