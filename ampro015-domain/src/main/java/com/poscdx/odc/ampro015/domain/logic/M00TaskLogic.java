package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.spec.M00TaskService;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import org.springframework.data.domain.Pageable;

import java.util.List;
public class M00TaskLogic implements M00TaskService {
    private final M00TaskStore store;

    public M00TaskLogic(M00TaskStore store) {
        this.store = store;
    }

    @Override
    public M00Task findTaskByProjectNumberAndTaskName(M00TaskId id) {
        return store.retrieve(id);
    }

    @Override
    public List<M00Task> findAll(String projectNumber) {
        return store.retrieveAll(projectNumber);
    }

    @Override
    public M00Task modify(M00Task requestUpdateTask) {
        return this.store.update(requestUpdateTask);
    }

    @Override
    public void modifyByList(List<M00Task> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public M00Task register(M00Task entity) {
        return store.create(entity);
    }

    @Override
    public void remove(M00TaskId id) {
        store.delete(id);
    }

    @Override
    public List<M00Task> findTaskByConditions(String projectNumber, String taskName, String planDate, String actualEndDate, Pageable pageable) {
        return store.findTaskByConditions(projectNumber, taskName, planDate, actualEndDate, pageable);
    }
}
