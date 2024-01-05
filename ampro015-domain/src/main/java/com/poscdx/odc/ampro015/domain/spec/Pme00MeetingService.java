package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import java.util.Date;
import java.util.List;

/**
 * Interface Pme00MeetingService
 * @author 202261_Son
 *  @since 2023-11-11
 */
public interface Pme00MeetingService {

    /**
     * Interface Find Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00Meeting find(int id);

    /**
     * Interface Find List Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    List<Pme00Meeting> findAll();

    /**
     * Interface Modify Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    void modify(List<Pme00Meeting> entityList);

    /**
     * Interface Register Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    Pme00Meeting register(Pme00Meeting entity);

    /**
     * Interface Remove Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    void remove(int id);

    /**
     * Interface Search Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    List<Pme00Meeting> findAllByAssetId(
                                int cd_tp_id,
                                String title,
                                String startTime,
                                String endTime,
                                String creatorId,
                                String requesterId,
                                String categoryMeeting,
                                String status);

    /**
     * Interface FindMetingByStartAndEnd Meeting
     * @author 202261_Son
     *  @since 2023-11-11
     */
    int findMetingByStartAndEndByRoom(String cdv, Date startDate, Date endDate);

    List<Pme00Meeting> findByEndDate();
}
