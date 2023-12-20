package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscodx.odc.ampro015.store.jpo.Pme00DashboardSettingJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pme00DashboardSettingRepository extends JpaRepository<Pme00DashboardSettingJpo, Integer> {

    Pme00DashboardSettingJpo findByEmpId(String empId);
}
