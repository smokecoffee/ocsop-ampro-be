package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.spec.M99DailyReportService;
import com.poscdx.odc.ampro015.domain.store.M99DailyReportStore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

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

    @Override
    public Map<String, Object> findDailyReport(M99DailyReport m99DailyReport, int pageNo, int pageSize) {
        Pageable pageable;
        if (pageSize == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }

        List<Object[]> dailyReportList = this.store.findDailyReport(m99DailyReport.getEmployeeId(), m99DailyReport.getProjectNumber(),
                                                                m99DailyReport.getFromDate(), m99DailyReport.getToDate(), pageable);
        List<M99DailyReport> infoList = new ArrayList<>();
        for (Object[] obj : dailyReportList) {
            infoList.add(new M99DailyReport(obj));
        }

        Map<String, Object> rs = new HashMap<>();

        int total = this.store.countDailyReport(m99DailyReport.getEmployeeId(), m99DailyReport.getProjectNumber(),
                                                    m99DailyReport.getFromDate(), m99DailyReport.getToDate());

        rs.put("total", total);
        rs.put("info", infoList);

        return rs;
    }

}
