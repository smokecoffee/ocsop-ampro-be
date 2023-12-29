package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00DashboardSetting;
import com.poscdx.odc.ampro015.domain.store.Pme00DashboardSettingStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00DashboardSettingJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00DashboardSettingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00DashboardSettingJpaStore implements Pme00DashboardSettingStore {

    private final Pme00DashboardSettingRepository repository;

    public Pme00DashboardSettingJpaStore(Pme00DashboardSettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00DashboardSetting retrieve(int id) {
        Optional<Pme00DashboardSettingJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00DashboardSettingJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00DashboardSetting> retrieveAll() {
        return Pme00DashboardSettingJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00DashboardSetting update(Pme00DashboardSetting Pme00DashboardSetting) {
        Pme00DashboardSettingJpo jpoToUpdate = new Pme00DashboardSettingJpo(Pme00DashboardSetting);
        Pme00DashboardSettingJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00DashboardSetting create(Pme00DashboardSetting entity) {
        return this.repository.save(new Pme00DashboardSettingJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }


    @Override
    public Pme00DashboardSetting findByEmpId(String empId) {
        Pme00DashboardSettingJpo result = this.repository.findByEmpId(empId);
        return (result != null) ? result.toDomain() : null;
    }
}
