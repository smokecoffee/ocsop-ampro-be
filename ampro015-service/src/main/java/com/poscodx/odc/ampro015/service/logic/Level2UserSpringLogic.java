package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Level2UserLogic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.poscdx.odc.ampro015.domain.logic.ExcanUserLogic;
import com.poscdx.odc.ampro015.domain.store.ExcanUserStore;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Level2UserSpringLogic extends Level2UserLogic
{
    public Level2UserSpringLogic() {
        super();
    }
}
