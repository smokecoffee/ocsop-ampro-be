package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00AnnouncementJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Pme00AnnouncementRepository extends JpaRepository<Pme00AnnouncementJpo, Integer> {

    @Query(value = "select\n" +
            "tpa.ID AS id,\n" +
            "tpa.NAME AS name,\n" +
            "tpa.`TYPE` AS type,\n" +
            "tpa.CREATOR_ID AS creatorId,\n" +
            "tpa.DESCRIPTION AS description,\n" +
            "tpa.STATUS AS status,\n" +
            "tpa.DATE AS date,\n" +
            "tme.NAME AS creatorName, \n" +
            "tme.PHOTO AS avatar \n" +
            "from tb_pme00_announcement tpa left join tb_m00_employee tme \n" +
            "on tpa.CREATOR_ID = tme.EMP_ID", nativeQuery = true)
    List<Map<String, String>> findAllAnnouncement();
    @Query(value = "select\n" +
            "tpa.ID AS id,\n" +
            "tpa.NAME AS name,\n" +
            "tpa.`TYPE` AS type,\n" +
            "tpa.CREATOR_ID AS creatorId,\n" +
            "tpa.DESCRIPTION AS description,\n" +
            "tpa.STATUS AS status,\n" +
            "tpa.DATE AS date,\n" +
            "tme.NAME AS creatorName, \n" +
            "tme.PHOTO AS avatar \n" +
            "from tb_pme00_announcement tpa left join tb_m00_employee tme \n" +
            "on tpa.CREATOR_ID = tme.EMP_ID \n" +
            "WHERE  1= 1 \n" +
            "AND (:name IS NULL OR (tpa.NAME LIKE CONCAT('%', :name, '%')))\n" +
            "AND (:type = 0 OR (tpa.`TYPE` = :type))\n" +
            "AND (:status IS NULL OR (tpa.STATUS LIKE CONCAT('%', :status, '%')))\n" +
            "AND (:startDate IS NULL OR (tpa.`DATE` = :startDate))\n",
            nativeQuery = true)
    List<Map<String, String>> findByConditions(@Param("name") String name,
                                                 @Param("type") int type,
                                                 @Param("status") String status,
                                                 @Param("startDate") Date startDate);
}