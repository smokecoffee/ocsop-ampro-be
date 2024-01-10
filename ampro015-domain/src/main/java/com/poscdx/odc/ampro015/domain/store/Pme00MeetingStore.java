package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;

import java.util.Date;
import java.util.List;

public interface Pme00MeetingStore {

    public Pme00Meeting retrieve(int id);

    public List<Pme00Meeting> retrieveAll();

    public Pme00Meeting update(Pme00Meeting pme00Meeting);

    public Pme00Meeting create(Pme00Meeting entity);

    public void delete(int id);

    public int findMetingByStartAndEnd(
            int cdTpId,
            Date startDate,
            Date endDate);

    public int findMetingByStartAndEndByRoom(
            String cdv,
            Date startDate,
            Date endDate);

    public List<Pme00Meeting> findByEndDate(String cdV);
}
