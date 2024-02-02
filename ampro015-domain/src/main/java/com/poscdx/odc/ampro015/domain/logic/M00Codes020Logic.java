package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;
import com.poscdx.odc.ampro015.domain.spec.M00Codes020Service;
import com.poscdx.odc.ampro015.domain.store.M00Codes020Store;

import java.util.List;

public class M00Codes020Logic implements M00Codes020Service{

    public final M00Codes020Store store;

    public M00Codes020Logic(M00Codes020Store store){
        this.store = store;
    }
    @Override
    public List<M00Codes020> findAll(){
        return this.store.retrieveAll();
    }

    @Override
    public M00Codes020 find(int cdTpId){
        return this.store.retrieve(cdTpId);
    }

}
