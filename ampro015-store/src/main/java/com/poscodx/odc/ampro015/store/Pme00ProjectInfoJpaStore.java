package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00ProjectInfoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Pme00ProjectInfoJpaStore
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
@Repository
public class Pme00ProjectInfoJpaStore implements Pme00ProjectInfoStore {
    private final Pme00ProjectInfoRepository repository;

    public Pme00ProjectInfoJpaStore(Pme00ProjectInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00ProjectInfo retrieve(String cdVId) {
        Optional<Pme00ProjectInfoJpo> retVal = this.repository.findById(cdVId);
        return retVal.map(Pme00ProjectInfoJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00ProjectInfo> retrieveAll() {
        return Pme00ProjectInfoJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00ProjectInfo update(Pme00ProjectInfo Pme00ProjectInfo) {
        Pme00ProjectInfoJpo jpoToUpdate = new Pme00ProjectInfoJpo(Pme00ProjectInfo);
        Pme00ProjectInfoJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00ProjectInfo create(Pme00ProjectInfo entity) {
        return this.repository.save(new Pme00ProjectInfoJpo(entity)).toDomain();
    }

    @Override
    public void delete(String cdVId) {
        this.repository.deleteById(cdVId);
    }

    @Override
    public List<Object[]> findProjectInfo(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, boolean searchAllStatus,
                                          List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, Pageable pageable){
        return this.repository.findProjectInfo(cdV, meaning, period, koreaPM, vietnamPL, framework, searchAllStatus,
                searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate, pageable);
    }

    @Override
    public List<Object[]> findProjectInfoWithEmpId(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, boolean searchAllStatus,
                                                   List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId,
                                                   Pageable pageable) {
        return this.repository.findProjectInfoWithEmpId(cdV, meaning, period, koreaPM, vietnamPL, framework, searchAllStatus,
                searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate, empId, pageable);
    }

    @Override
    public List<Object[]> getKoreaPM(String projectNumber) {
        return this.repository.getKoreaPM(projectNumber);
    }

    @Override
    public List<Object[]> getVietnamPL(String projectNumber) {
        return this.repository.getVietnamPL(projectNumber);
    }

    @Override
    public List<Object[]> getProjectMember(String cdV) {
        return this.repository.getMember(cdV);
    }

    @Override
    public int getCountProject(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, boolean searchAllStatus,
                               List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate){
        return this.repository.countProject(cdV, meaning, period, koreaPM, vietnamPL, framework, searchAllStatus,
                                            searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate);
    }

    @Override
    public int getCountProjectWithEmpId(String cdV, String meaning, int period, String koreaPM, String vietnamPL, String framework, boolean searchAllStatus,
                                        List<String> searchStatus, Date fromStartDate, Date toStartDate, Date fromEndDate, Date toEndDate, String empId) {
        return this.repository.countProjectWithEmpId(cdV, meaning, period, koreaPM, vietnamPL, framework, searchAllStatus,
                searchStatus, fromStartDate, toStartDate, fromEndDate, toEndDate, empId);
    }
}