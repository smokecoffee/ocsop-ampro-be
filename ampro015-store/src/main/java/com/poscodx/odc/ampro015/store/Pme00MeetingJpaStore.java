package com.poscodx.odc.ampro015.store;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.store.Pme00MeetingStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00MeetingJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00MeetingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implements Pme00MeetingStore
 * @author 202261_Son
 *  @since 2023-11-11
 */
@Repository
public class Pme00MeetingJpaStore implements Pme00MeetingStore {
    private final Pme00MeetingRepository repository;

    /**
     * Function Pme00MeetingJpaStore
     * @author 202261_Son
     *  @since 2023-11-11
     */
    public Pme00MeetingJpaStore(Pme00MeetingRepository repository) {
        this.repository = repository;
    }

    /**
     * Function Retrieve Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00Meeting retrieve(int id) {
        Optional<Pme00MeetingJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00MeetingJpo::toDomain).orElse(null);
    }

    /**
     * Function RetrieveAll Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public List<Pme00Meeting> retrieveAll() {
        return Pme00MeetingJpo.toDomains(this.repository.findAll());
    }

    /**
     * Function Update Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00Meeting update(Pme00Meeting pme00Meeting) {
        Pme00MeetingJpo jpoToUpdate = new Pme00MeetingJpo(pme00Meeting);
        Pme00MeetingJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    /**
     * Function Create Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public Pme00Meeting create(Pme00Meeting entity) {
        Pme00MeetingJpo createData = new Pme00MeetingJpo(entity);
        System.out.println("data update: " + createData);
        return this.repository.save(createData).toDomain();
    }

    /**
     * Function Delete Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    /**
     * Function findAllByAssetId
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public List<Pme00Meeting> findAllByAssetId(
            Integer cd_tp_id,
            String title,
            String startTime,
            String endTime,
            String creatorId,
            String requesterId,
            String categoryMeeting,
            String status) {
        return Pme00MeetingJpo.toDomains(this.repository.findAllByAssetId(
                cd_tp_id,
                title,
                startTime,
                endTime,
                creatorId,
                requesterId,
                categoryMeeting,
                status));
    }

    /**
     * Function FindMetingByStartAndEnd
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Override
    public int findMetingByStartAndEnd(int cdTpId, Date startDate, Date endDate) {
        return this.repository.findMetingByStartAndEnd(cdTpId, startDate, endDate);
    }

    @Override
    public List<Pme00Meeting> findByEndDate(String cdV) {
        return Pme00MeetingJpo.toDomains(this.repository.findByEndDate(cdV));
    }


}
