package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Level2EmployeeLogic;
import com.poscdx.odc.ampro015.domain.logic.Level2MeetingLogic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Level2EmployeeSpringLogic extends Level2EmployeeLogic {

    public Level2EmployeeSpringLogic() { super(); }
}
