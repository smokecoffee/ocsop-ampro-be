package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00WorkingTime;
import com.poscdx.odc.ampro015.domain.spec.Pme00WorkingTimeService;
import com.poscdx.odc.ampro015.domain.store.Pme00WorkingTimeStore;

import java.util.List;

public class Pme00WorkingTimeLogic implements Pme00WorkingTimeService {

    public final Pme00WorkingTimeStore store;

    public Pme00WorkingTimeLogic(Pme00WorkingTimeStore store){
        this.store = store;
    }
    @Override
    public List<Pme00WorkingTime> findAll(){
        return this.store.retrieveAll();
    }

}
