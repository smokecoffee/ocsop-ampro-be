package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M00Codes030Logic;
import com.poscdx.odc.ampro015.domain.store.M00Codes030Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M00Codes030SpringLogic extends M00Codes030Logic {

    public M00Codes030SpringLogic(M00Codes030Store store){
        super(store);
    }
}
