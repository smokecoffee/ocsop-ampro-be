package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;

import java.util.List;

public interface Pme00ProjectListService {
    Pme00ProjectListDto find(String cdVId);

    List<Pme00ProjectListDto> findAll();

    void modify(List<Pme00ProjectListDto> pme00ProjectInfo);

    Pme00ProjectListDto register(Pme00ProjectListDto entity);

    void remove(String cdVId);

    List<Pme00Member> getMember(String cdVId);
}
