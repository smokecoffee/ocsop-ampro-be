package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M00Codes020Logic;
import com.poscdx.odc.ampro015.domain.store.M00Codes020Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M00Codes020SpringLogic extends M00Codes020Logic {

    public M00Codes020SpringLogic(M00Codes020Store store){
        super(store);
    }
}
