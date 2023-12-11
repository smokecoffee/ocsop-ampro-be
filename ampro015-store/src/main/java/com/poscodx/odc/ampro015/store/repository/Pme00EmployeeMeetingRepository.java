package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeMeetingJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Pme00EmployeeMeetingRepository extends JpaRepository<Pme00EmployeeMeetingJpo, Integer> {
    List<Pme00EmployeeMeetingJpo> findByMeetingId(int meeting_id);

    @Query(value = "DELETE FROM TB_PME00_EMPLOYEE_MEETING  WHERE MEETING_ID = :meetingId",nativeQuery = true)
    void deleteAllByMeetingId(@Param("meetingId") int meetingId);
}
