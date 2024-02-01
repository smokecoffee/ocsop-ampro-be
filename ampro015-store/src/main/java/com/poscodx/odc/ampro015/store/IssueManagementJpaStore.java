package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import com.poscodx.odc.ampro015.store.repository.IssueManagementRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
    public List<Object[]> search(String content, List<String> site, List<String> module, boolean module_check, boolean division_check,
                                 List<String> division_flag, String applied_period_flag, String accept_flag, String requester_confirm,
                                 String requester, String requester_id, String contents_kr, String developer, Date registrationFromStartDate,
                                 Date registrationToEndDate, Date requestFromStartDate, Date requestToEndDate, Pageable pageable) {
        return this.repository.search(content, site, module, module_check, division_check, division_flag, applied_period_flag, accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, registrationFromStartDate, registrationToEndDate, requestFromStartDate, registrationToEndDate, pageable);
    }

    @Override
    public int totalIssue(String content, List<String> site, List<String> module, boolean module_check, boolean division_check,
                          List<String> division_flag, String applied_period_flag, String accept_flag, String requester_confirm,
                          String requester, String requester_id, String contents_kr, String developer, Date registrationFromStartDate,
                          Date registrationToEndDate, Date requestFromStartDate, Date requestToEndDate) {
        return repository.countSearch(content, site, module, module_check, division_check, division_flag, applied_period_flag, accept_flag, requester_confirm, requester, requester_id, contents_kr, developer, registrationFromStartDate, registrationToEndDate, requestFromStartDate, requestToEndDate);
    }


    @Override
    public int maxSeq() {
        return repository.maxSeq();
    }

}
