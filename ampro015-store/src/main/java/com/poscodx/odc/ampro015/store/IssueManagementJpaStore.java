package com.poscodx.odc.ampro015.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import com.poscdx.odc.ampro015.domain.utils.DateUtils;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import com.poscodx.odc.ampro015.store.repository.IssueManagementRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class IssueManagementJpaStore implements IssueManagementStore {

    private final IssueManagementRepository repository;

    public IssueManagementJpaStore(IssueManagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IssueManagement> retrieve(int seq, String site) {
        Iterable<IssueManagementJpo> list = this.repository.findBySeqAndSite(seq, site);
        return IssueManagementJpo.toDomains(list);
    }

    @Override
    public List<IssueManagement> retrieveAll() {
        return IssueManagementJpo.toDomains(this.repository.findAll());
    }

    @Override
    public IssueManagement update(IssueManagement entity) {
        IssueManagementJpo issueManagementJpo = new IssueManagementJpo(entity);
        IssueManagementJpo updateJpo = this.repository.save(issueManagementJpo);
        return updateJpo.toDomain();
    }

    @Override
    public IssueManagement create(IssueManagement entity) {
        return this.repository.save(new IssueManagementJpo(entity)).toDomain();
    }

    @Override
    public void delete(IssueManagementId id) {
        this.repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public List<Object[]> findIssueInfo(String contents, String site, String modules, String division_flag, String applied_period_flag,
                                        String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer,
                                        Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate,
                                        Date requestToEndDate, Pageable pageable) {

        String registrationStartDate = DateUtils.convertDateTimeToString(registrationFromStartDate, DateUtils.dbDate);
        String registrationEndDate = DateUtils.convertDateTimeToString(registrationToEndDate, DateUtils.dbDate);
        String requestStartDate =DateUtils.convertDateTimeToString(requestFromStartDate, DateUtils.dbDate);
        String requestEndDate = DateUtils.convertDateTimeToString(requestToEndDate, DateUtils.dbDate);

        return this.repository.findIssueInfo(contents, site, modules, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, registrationStartDate,
                registrationEndDate, requestStartDate, requestEndDate, pageable);
    }

    @Override
    public List<IssueManagement> searchIssue(String site, String module, String division_flag, String applied_period_flag, String accept_flag,
                                             String request_confirm, String requester, String contents, String contents_kr, String developer) {

        List<Map<String, String>> mapList = this.repository.searchIssue(site, module, division_flag, applied_period_flag,
                accept_flag, request_confirm, requester, contents, contents_kr, developer);
        ObjectMapper mapper = new ObjectMapper();
        return mapList.stream().map(item -> mapper.convertValue(item, IssueManagement.class)).collect(Collectors.toList());
    }

    @Override
    public List<Object[]> findIssueManagementDto(String contents, String site, String modules, String division_flag,
                                                 String applied_period_flag, String accept_flag, String requester_confirm,
                                                 String requester, String contents_kr, String developer, Date registration_date, Date request_date) {

        return this.repository.findIssueManagementDto(contents, site, modules, division_flag, applied_period_flag, accept_flag,
                requester_confirm, requester, contents_kr, developer, registration_date, request_date);
    }

    @Override
    public int findIssueReport(String contents, String site, String modules, String division_flag, String applied_period_flag,
                               String accept_flag, String requester_confirm, String requester, String requester_id, String contents_kr, String developer,
                               Date registrationFromStartDate, Date registrationToEndDate, Date requestFromStartDate, Date requestToEndDate) {

        String registrationStartDate = DateUtils.convertDateTimeToString(registrationFromStartDate, DateUtils.dbDate);
        String registrationEndDate = DateUtils.convertDateTimeToString(registrationToEndDate, DateUtils.dbDate);
        String requestStartDate =DateUtils.convertDateTimeToString(requestFromStartDate, DateUtils.dbDate);
        String requestEndDate = DateUtils.convertDateTimeToString(requestToEndDate, DateUtils.dbDate);
        return this.repository.countIssueReport(contents, site, modules, division_flag, applied_period_flag,
                accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, registrationStartDate,
                registrationEndDate, requestStartDate, requestEndDate);
    }

    @Override
    public int maxSeq() {
        return repository.maxSeq();
    }

}
