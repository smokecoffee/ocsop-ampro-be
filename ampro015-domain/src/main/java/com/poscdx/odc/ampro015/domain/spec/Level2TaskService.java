package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;
import java.util.Map;

public interface Level2TaskService {

    M00Task find(ServiceLifecycle serviceLifecycle, M00TaskId id);

    Map<String, Object> findAll(ServiceLifecycle serviceLifecycle, String projectNumber, String taskName, String planDate, String actualEndDate,
                                int pageNo, int pageSize, String sortBy, String sortDirection);

    void modify(ServiceLifecycle serviceLifecycle, List<M00Task> entityList);

    M00Task register(ServiceLifecycle serviceLifecycle, M00Task entity);

    void remove(ServiceLifecycle serviceLifecycle, M00TaskId id);
}
