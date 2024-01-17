package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M00EmployeeLogic;
import com.poscdx.odc.ampro015.domain.store.M00EmployeeStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M00EmployeeSpringLogic extends M00EmployeeLogic {
    public M00EmployeeSpringLogic(M00EmployeeStore store) {
        super(store);
    }
}
