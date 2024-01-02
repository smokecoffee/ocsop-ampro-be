package com.poscdx.odc.ampro015.domain.logic;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.spec.Pme00MeetingService;
import com.poscdx.odc.ampro015.domain.store.Pme00MeetingStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interface Pme00MeetingService
 * @author 202261_Son
 *  @since 2023-11-11
 */
public class Pme00MeetingLogic implements Pme00MeetingService {

    private final Pme00MeetingStore store;
    /**
     * Implement Interface Pme00MeetingStore
     * @author 202261_Son
     *  @since 2023-11-11
     */
    public Pme00MeetingLogic(Pme00MeetingStore store) {
        this.store = store;
    }

    /**
     * Get Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00Meeting find(int id) {
        return this.store.retrieve(id);
    }

    /**
     * Get List Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public List<Pme00Meeting> findAll() {
        return this.store.retrieveAll();
    }

    /**
     * Modify Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public void modify(List<Pme00Meeting> entityList) {
        entityList.forEach(this.store::update);
    }

    /**
     * Register Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00Meeting register(Pme00Meeting entity) {
        return this.store.create(entity);
    }

    /**
     * Remove Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    /**
     * Search Meeting Booking Room function
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public List<Pme00Meeting> findAllByAssetId(int cd_tp_id,
                                               String title,
                                               String startTime,
                                               String endTime,
                                               String creatorId,
                                               String requesterId,
                                               String categoryMeeting,
                                               String status) {
        return this.store.findAllByAssetId(cd_tp_id,
                                           title,
                                           startTime,
                                           endTime,
                                           creatorId,
                                           requesterId,
                                           categoryMeeting,
                                           status);
    }

    /**
     *  FindMetingByStartAndEnd
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public int findMetingByStartAndEnd(int cdTpId, Date startDate, Date endDate) {
        return this.store.findMetingByStartAndEnd(cdTpId, startDate, endDate);
    }

    @Override
    public List<Pme00Meeting> findByEndDate() {
        List<Pme00Meeting> meetingList = new ArrayList<>();
        meetingList.addAll(this.store.findByEndDate(65));
        meetingList.addAll(this.store.findByEndDate(66));
        meetingList.addAll(this.store.findByEndDate(67));
        return meetingList;
    }


}
