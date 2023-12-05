package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;

import java.util.List;

public interface Pme00ProjectInfoStore {
    Pme00ProjectInfo retrieve(String cdVId);

    List<Pme00ProjectInfo> retrieveAll();

    Pme00ProjectInfo update(Pme00ProjectInfo entity);

    Pme00ProjectInfo create(Pme00ProjectInfo entity);

    void delete (String cdVId);

    List<Object[]> getActiveEmployee();

    List<Pme00ProjectListDto> getProjectList(Pme00ProjectListDto dto);

}
