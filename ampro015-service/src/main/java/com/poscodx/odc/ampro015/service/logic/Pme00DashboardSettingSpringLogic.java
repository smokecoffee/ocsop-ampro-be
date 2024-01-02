package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.Pme00DashboardSettingLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00DashboardSettingStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00DashboardSettingSpringLogic extends Pme00DashboardSettingLogic {
    public Pme00DashboardSettingSpringLogic(Pme00DashboardSettingStore store) {
        super(store);
    }
}
