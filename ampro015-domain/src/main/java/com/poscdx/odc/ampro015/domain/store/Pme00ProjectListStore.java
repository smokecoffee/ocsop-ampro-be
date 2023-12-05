package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;

import java.util.List;

public interface Pme00ProjectListStore {
    Pme00ProjectListDto retrieve(String cdVId);

    List<Pme00ProjectListDto> retrieveAll();

    Pme00ProjectListDto update(Pme00ProjectListDto entity);

    Pme00ProjectListDto create(Pme00ProjectListDto entity);

    void delete (String cdVId);

    List<Object[]> getMember();
}
