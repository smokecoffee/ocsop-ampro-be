package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscodx.odc.ampro015.store.repository.M00TaskRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class M00TaskJpaStore implements M00TaskStore {
    private final M00TaskRepository repository;

    public M00TaskJpaStore(M00TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public M00Task retrieve(M00TaskId id) {
        Optional<M00TaskJpo> results = this.repository.findById(id);
        return results.isPresent() ? results.get().toDomain() : null;
    }

    @Override
    public M00Task update(M00Task entity) {
        M00TaskJpo jpoToUpdate = new M00TaskJpo(entity);
        M00TaskJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public M00Task create(M00Task entity) {
        return this.repository.save(new M00TaskJpo(entity)).toDomain();
    }


    @Override
    public void delete(M00TaskId id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<M00Task> retrieveAll(String projectNumber) {
        return this.repository.findAllByProjectNumberContains(projectNumber)
                .stream().map(M00TaskJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<M00Task> findTaskByConditions(String projectNumber, String taskName, String planDate,
                                              String actualEndDate, String status, String empId, Pageable pageable) {
        return this.repository.findTaskByConditions(projectNumber, taskName, planDate, actualEndDate)
                .stream().map(M00TaskJpo::toDomain).collect(Collectors.toList());
    }
}
