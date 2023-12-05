package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscodx.odc.ampro015.store.repository.M00TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class M00TaskJpaStore implements M00TaskStore {
    private final M00TaskRepository repository;

    public M00TaskJpaStore(M00TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public M00Task retrieve(M00TaskId id) {
        Optional<M00TaskJpo> taskResult = this.repository.findOneTask(id.getProjectNumber(), id.getTaskName());
        return taskResult.map(M00TaskJpo::toDomain).orElse(null);
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
    public List<M00Task> retrieveAll(String projectNumber,  int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create pageable
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return M00TaskJpo.toDomains(this.repository.findAllByProjectNumber(projectNumber, pageable));
    }
}
