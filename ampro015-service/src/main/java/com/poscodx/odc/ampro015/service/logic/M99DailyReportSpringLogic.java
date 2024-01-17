package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.M99DailyReportLogic;
import com.poscdx.odc.ampro015.domain.store.M99DailyReportStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class M99DailyReportSpringLogic extends M99DailyReportLogic {
    public M99DailyReportSpringLogic(M99DailyReportStore store) {
        super(store);
    }
}
