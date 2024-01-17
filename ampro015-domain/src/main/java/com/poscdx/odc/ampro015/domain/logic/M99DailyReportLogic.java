package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.spec.M99DailyReportService;
import com.poscdx.odc.ampro015.domain.store.M99DailyReportStore;

import java.util.List;

public class M99DailyReportLogic implements M99DailyReportService {
    private final M99DailyReportStore store;

    public M99DailyReportLogic(M99DailyReportStore store) {
        this.store = store;
    }

    @Override
    public M99DailyReport find(int seq) {
        return store.retrieve(seq);
    }

    @Override
    public List<M99DailyReport> findAll() {
        return store.retrieveAll();
    }

    @Override
    public M99DailyReport modify(M99DailyReport requestUpdateTask) {
        return this.store.update(requestUpdateTask);
    }

    @Override
    public void modifyByList(List<M99DailyReport> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public M99DailyReport register(M99DailyReport entity) {
        return store.create(entity);
    }

    @Override
    public void remove(int seq) {
        store.delete(seq);
    }

    @Override
    public List<M99DailyReport> findWorkingTimeByEmployeeId(String empId) {
        return store.findWorkingTimeByEmployeeId(empId);
    }

}
