package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00AnnouncementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface Pme00AnnouncementRepository extends JpaRepository<Pme00AnnouncementJpo, Integer> {

    @Query(value = "select\n" +
//            "tpa.*,\n" +
            "tpa.ID AS id,\n" +
            "tpa.NAME AS name,\n" +
            "tpa.`TYPE` AS type,\n" +
            "tpa.CREATOR_ID AS creatorId,\n" +
            "tpa.DESCRIPTION AS description,\n" +
            "tpa.STATUS AS status,\n" +
            "tpa.DATE AS date,\n" +
            "tme.NAME AS creatorName \n" +
            "from tb_pme00_announcement tpa left join tb_m00_employee tme \n" +
            "on tpa.CREATOR_ID = tme.EMP_ID", nativeQuery = true)
    List<Map<String, String>> findAllAnnouncement();
}
