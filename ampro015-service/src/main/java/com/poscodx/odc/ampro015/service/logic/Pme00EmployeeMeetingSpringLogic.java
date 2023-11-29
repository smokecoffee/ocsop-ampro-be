package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00EmployeeMeetingLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeMeetingStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00EmployeeMeetingSpringLogic extends Pme00EmployeeMeetingLogic {
    public Pme00EmployeeMeetingSpringLogic(Pme00EmployeeMeetingStore store) {
        super(store);
    }
}
