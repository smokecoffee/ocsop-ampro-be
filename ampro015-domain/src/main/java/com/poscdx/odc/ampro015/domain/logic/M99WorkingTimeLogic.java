package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M99WorkingTime;
import com.poscdx.odc.ampro015.domain.spec.M99WorkingTimeService;
import com.poscdx.odc.ampro015.domain.store.M99WorkingTimeStore;

import java.util.List;

public class M99WorkingTimeLogic implements M99WorkingTimeService{

    public final M99WorkingTimeStore store;

    public M99WorkingTimeLogic(M99WorkingTimeStore store){
        this.store = store;
    }
    @Override
    public List<M99WorkingTime> findAll(){
        return this.store.retrieveAll();
    }

}
