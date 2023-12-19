package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Level2Logic;
import com.poscdx.odc.ampro015.domain.logic.Level2TaskLogic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Level2TaskSpringLogic extends Level2TaskLogic {
    public Level2TaskSpringLogic() {
        super();
    }
}
