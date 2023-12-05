package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeMeetingJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pme00EmployeeMeetingRepository extends JpaRepository<Pme00EmployeeMeetingJpo, Integer> {

    List<Pme00EmployeeMeetingJpo> findByMeetingId(int meeting_id);

}
