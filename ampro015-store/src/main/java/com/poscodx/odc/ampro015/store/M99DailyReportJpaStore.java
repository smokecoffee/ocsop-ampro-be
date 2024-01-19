package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.store.M99DailyReportStore;
import com.poscodx.odc.ampro015.store.jpo.M99DailyReportJpo;
import com.poscodx.odc.ampro015.store.repository.M99DailyReportRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class M99DailyReportJpaStore implements M99DailyReportStore {
    private final M99DailyReportRepository repository;

    public M99DailyReportJpaStore(M99DailyReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public M99DailyReport retrieve(int seq) {
        Optional<M99DailyReportJpo> retVal = this.repository.findById(seq);
        return retVal.map(M99DailyReportJpo::toDomain).orElse(null);
    }

    @Override
    public M99DailyReport update(M99DailyReport entity) {
        M99DailyReportJpo jpoToUpdate = new M99DailyReportJpo(entity);
        M99DailyReportJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public M99DailyReport create(M99DailyReport entity) {
        return this.repository.save(new M99DailyReportJpo(entity)).toDomain();
    }


    @Override
    public void delete(int seq) {
        this.repository.deleteById(seq);
    }

    @Override
    public List<M99DailyReport> retrieveAll() {
        return M99DailyReportJpo.toDomains(this.repository.findAll());
    }

    @Override
    public List<M99DailyReport> findWorkingTimeByEmployeeId(String empId) {
        Iterable<M99DailyReportJpo> list =  this.repository
                                                .findByEmployeeIdAndOpSatDateIsNotNullAndOpDnDateIsNotNull(empId);
        return M99DailyReportJpo.toDomains(list);
    }

    @Override
    public List<Object[]> findDailyReport(String employeeId, String projectNumber, Date fromDate, Date toDate) {
        return this.repository.findDailyReport(employeeId, projectNumber, fromDate, toDate);
    }
}
