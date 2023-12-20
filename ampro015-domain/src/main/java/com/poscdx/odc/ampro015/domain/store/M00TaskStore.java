package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.spec.TaskSpecification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface M00TaskStore {
    M00Task retrieve(M00TaskId id);

    M00Task update(M00Task entity);

    M00Task create(M00Task entity);

    void delete(M00TaskId id);

    List<M00Task> retrieveAll(String projectNumber);

    List<M00Task> findTaskByConditions(String projectNumber, String taskName, String planDate, String actualEndDate, String status, Pageable pageable);
}
