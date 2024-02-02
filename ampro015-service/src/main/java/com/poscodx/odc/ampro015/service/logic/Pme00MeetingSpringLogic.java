package com.poscodx.odc.ampro015.service.logic;
import com.poscdx.odc.ampro015.domain.logic.Pme00MeetingLogic;
import com.poscdx.odc.ampro015.domain.store.Pme00MeetingStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class Pme00MeetingSpringLogic extends Pme00MeetingLogic {
    public Pme00MeetingSpringLogic(Pme00MeetingStore store) {
        super(store);
    }

}
