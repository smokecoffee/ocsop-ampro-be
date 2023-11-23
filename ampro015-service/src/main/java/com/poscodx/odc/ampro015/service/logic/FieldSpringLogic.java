package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.FieldLogic;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FieldSpringLogic  extends FieldLogic {
    public FieldSpringLogic(FieldStore store) {
        super(store);
    }
}
