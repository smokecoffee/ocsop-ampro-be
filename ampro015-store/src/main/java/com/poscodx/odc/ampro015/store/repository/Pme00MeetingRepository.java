package com.poscodx.odc.ampro015.store.repository;
import com.poscdx.odc.ampro015.domain.entity.Pme00AllMeetingResponse;
import com.poscodx.odc.ampro015.store.jpo.Pme00MeetingJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface Pme00MeetingRepository extends JpaRepository<Pme00MeetingJpo, Integer> {

    @Query(value = "SELECT *\n" +
                   "FROM tb_pme00_meeting f\n" +
                   "WHERE 1=1\n" +
                         "AND f.CD_TP_ID LIKE CONCAT('%',:cd_tp_id,'%')\n" +
                         "AND f.TITLE LIKE CONCAT('%',:title,'%')\n" +
                         "AND f.START_TIME LIKE CONCAT('%',:startTime,'%')\n" +
                         "AND f.END_TIME LIKE CONCAT('%',:endTime,'%')\n" +
                         "AND f.CREATOR_ID LIKE CONCAT('%',:creatorId,'%')\n" +
                         "AND f.REQUESTER_ID LIKE CONCAT('%',:requesterId,'%')\n" +
                         "AND f.CATEGORY_MEETING LIKE CONCAT('%',:categoryMeeting,'%')\n" +
                         "AND f.STATUS LIKE CONCAT('%',:status,'%')\n", nativeQuery = true)



    Iterable<Pme00MeetingJpo> findAllByAssetId(@Param("cd_tp_id") Integer cd_tp_id,
                                               @Param("title") String title,
                                               @Param("startTime") String startTime,
                                               @Param("endTime") String endTime,
                                               @Param("creatorId") String creatorId,
                                               @Param("requesterId") String requesterId,
                                               @Param("categoryMeeting") String categoryMeeting,
                                               @Param("status") String status
                                               );


    @Query(value = "SELECT count(MEETING_ID)\n" +
            "FROM tb_pme00_meeting \n" +
            "WHERE CD_TP_ID = :cdTpId " +
            "AND ((START_TIME >=  :startDate \n" +
            "    AND START_TIME <= :endDate ) \n" +
            "OR (END_TIME >=  :startDate \n" +
            "   AND END_TIME <= :endDate ))"
            , nativeQuery = true)

    int findMetingByStartAndEnd(@Param("cdTpId") int cdTpId, @Param("startDate") Date startDate,
                                               @Param("endDate") Date endDate);


}
