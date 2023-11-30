package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00EmployeeTaskLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00EmployeeTaskSpringLogic extends Pme00EmployeeTaskLogic {
    public Pme00EmployeeTaskSpringLogic(Pme00EmployeeTaskStore store) {
        super(store);
    }
}
