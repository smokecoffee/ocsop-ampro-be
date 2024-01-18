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
    public IssueManagement retrieve(int id) {
        Optional<IssueManagementJpo> retVal = this.repository.findBySeq(id);
        if(retVal.isPresent()){
            return retVal.get().toDomain();
        }
        return null;
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
        IssueManagementIdJpo jpo = new IssueManagementIdJpo(id.getSeq(), id.getSite());
        this.repository.deleteById(id);
    }

    @Override
    public List<IssueManagement> findIssueInfo(String content) {
        Iterable<IssueManagementJpo> list = this.repository.findIssueInfo(content);
        return IssueManagementJpo.toDomains(list);
    }
}
