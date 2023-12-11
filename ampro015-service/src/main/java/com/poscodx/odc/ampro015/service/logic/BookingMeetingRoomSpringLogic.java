package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.BookingMeetingRoomLogic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BookingMeetingRoomSpringLogic extends BookingMeetingRoomLogic {
    public BookingMeetingRoomSpringLogic() { super(); }
}
