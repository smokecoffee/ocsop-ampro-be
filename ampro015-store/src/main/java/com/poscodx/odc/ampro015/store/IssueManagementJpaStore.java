package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.IssueManagement;
import com.poscdx.odc.ampro015.domain.entity.IssueManagementId;
import com.poscdx.odc.ampro015.domain.store.IssueManagementStore;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementIdJpo;
import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import com.poscodx.odc.ampro015.store.repository.IssueManagementRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public void delete(IssueManagementId seq) {
        this.repository.deleteById(seq);
    }

    @Override
    public List<IssueManagement> findIssueInfo(String content) {
        Iterable<IssueManagementJpo> list = this.repository.findIssueInfo(content);
        return IssueManagementJpo.toDomains(list);
    }
}
