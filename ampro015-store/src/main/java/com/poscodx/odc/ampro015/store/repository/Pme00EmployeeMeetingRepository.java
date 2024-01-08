package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeMeetingJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Pme00EmployeeMeetingRepository extends JpaRepository<Pme00EmployeeMeetingJpo, Integer> {

    @Query(value = "SELECT t.EMP_MEET_ID \n" +
            ",t.MEETING_ID \n" +
            ",t.EMP_ID \n" +
            ",t.EMP_NAME \n" +
            ",CONCAT('http://172.25.219.61:8080/img/', p.PHOTO) as avatar \n" +
            "FROM tb_pme00_employee_meeting as t \n "+
            "JOIN tb_m00_employee as p on t.EMP_ID = p.EMP_ID \n"+
            "WHERE t.MEETING_ID = :meeting_id \n", nativeQuery = true)
    List<Pme00EmployeeMeetingJpo> findByMeetingId(@Param("meeting_id") int meeting_id);

    @Query(value = "DELETE FROM TB_PME00_EMPLOYEE_MEETING  WHERE MEETING_ID = :meetingId",nativeQuery = true)
    void deleteAllByMeetingId(@Param("meetingId") int meetingId);



}
