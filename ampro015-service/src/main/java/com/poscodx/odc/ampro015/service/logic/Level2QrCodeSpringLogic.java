package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Level2QrCodeLogic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Level2QrCodeSpringLogic extends Level2QrCodeLogic {
    public Level2QrCodeSpringLogic() {
        super();
    }
}
