package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Task;
import com.poscdx.odc.ampro015.domain.store.TaskStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.TaskId;
import com.poscodx.odc.ampro015.store.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskJpaStore implements TaskStore {
    private final TaskRepository repository;

    public TaskJpaStore(TaskRepository repository) {
        this.repository = repository;
    }


    @Override
    public Task retrieve(TaskId taskId) {
        Optional<Pme00TaskJpo> taskResult = this.repository.findById(taskId);
        if (taskResult.isPresent()) {
            return taskResult.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public Task update(Task entity) {
        Pme00TaskJpo jpoToUpdate = new Pme00TaskJpo(entity);
        Pme00TaskJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Task create(Task entity) {
        return null;
    }

    @Override
    public void delete(String projectId, String taskName) {

    }

    @Override
    public List<Task> retrieveList(String projectNumber) {
        return null;
    }
}
