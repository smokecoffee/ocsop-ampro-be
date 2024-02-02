package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00MeetingJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;


/**
 * Interface Pme00MeetingRepository
 * @author 202261_Son
 *  @since 2023-11-11
 */
public interface Pme00MeetingRepository extends JpaRepository<Pme00MeetingJpo, Integer> {


    /**
     * Interface findMetingByStartAndEnd
     * @author 202261_Son
     *  @since 2023-11-11
     */
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

    /**
     * Interface findMetingByStartAndEnd
     * @author 202261_Son
     *  @since 2023-11-11
     */
    @Query(value = "SELECT count(MEETING_ID)\n" +
            "FROM tb_pme00_meeting \n" +
            "WHERE CD_V = :cdv " +
            "AND ((START_TIME >=  :startDate \n" +
            "    AND START_TIME <= :endDate ) \n" +
            "OR (END_TIME >=  :startDate \n" +
            "   AND END_TIME <= :endDate ))"
            , nativeQuery = true)

    int findMetingByStartAndEndByRoom(@Param("cdv") String cdv, @Param("startDate") Date startDate,
                                @Param("endDate") Date endDate);

    @Query(value = "SELECT *\n" +
                    "FROM tb_pme00_meeting\n" +
                    "WHERE (END_TIME >= NOW() AND\n" +
                    "END_TIME <= DATE_FORMAT(NOW(), '%Y-%m-%d 23:59:59'))\n" +
                    "AND CD_V = :cdV\n" +
                    "ORDER BY END_TIME \n" +
                    "LIMIT 4", nativeQuery = true)
    List<Pme00MeetingJpo> findByEndDate(@Param("cdV") String cdV);


}
