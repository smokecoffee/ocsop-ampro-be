package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.M99DailyReportService;
import com.poscdx.odc.ampro015.domain.store.M99DailyReportStore;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

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
    public M99DailyReport modify(ServiceLifecycle serviceLifecycle, M99DailyReport dto, MultipartFile fileUpload) {
        M99DailyReport oldReport = serviceLifecycle.requestM99DailyReportService().find(dto.getSeq());
        List<String> fileNameList = new ArrayList<>();
        if (oldReport.getFileName() != null && !oldReport.getFileName().isEmpty()) {
            serviceLifecycle.requestLevel2Service()
                            .removeFile(Utils.UPLOAD_BUCKET, "Report", fileNameList);
        }
        if (fileUpload != null) {
            String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Report", fileUpload);
            if (!result.contains("Report")) {
                return null;
            }
        }
        return this.store.update(dto);
    }

    @Override
    public M99DailyReport register(ServiceLifecycle serviceLifecycle, M99DailyReport dto, MultipartFile fileUpload) {
        if (fileUpload != null) {
            String result = serviceLifecycle.requestLevel2Service().uploadFile(Utils.UPLOAD_BUCKET, "Report", fileUpload);
            if (!result.contains("Report")) {
                return null;
            }
        }
        return this.store.create(dto);
    }

    @Override
    public void remove(ServiceLifecycle serviceLifecycle, int seq) {
        M99DailyReport oldReport = serviceLifecycle.requestM99DailyReportService().find(seq);
        List<String> fileNameList = new ArrayList<>();
        if (oldReport.getFileName() != null && !oldReport.getFileName().isEmpty()) {
            serviceLifecycle.requestLevel2Service()
                    .removeFile(Utils.UPLOAD_BUCKET, "Report", fileNameList);
        }
        store.delete(seq);
    }

    @Override
    public List<M99DailyReport> findWorkingTime(String empId,  String startTime, String endTime) {
        return this.store.findWorkingTime(empId, startTime, endTime);
    }

    @Override
    public Map<String, Object> findDailyReport(M99DailyReport m99DailyReport, int pageNo, int pageSize) {
        Pageable pageable;
        Sort sort = Sort.by("REGISTER_DATE").descending();
        if (pageSize == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(pageNo, pageSize, sort);
        }

        List<Object[]> dailyReportList = this.store.findDailyReport(m99DailyReport.getEmployeeId(), m99DailyReport.getProjectNumber(),
                                                                m99DailyReport.getFromDate(), m99DailyReport.getToDate(), pageable);
        List<M99DailyReport> infoList = new ArrayList<>();
        for (Object[] obj : dailyReportList) {
            infoList.add(new M99DailyReport(obj));
        }

        Map<String, Object> rs = new HashMap<>();

        rs.put("total", infoList.size());
        rs.put("info", infoList);

        return rs;
    }

}
