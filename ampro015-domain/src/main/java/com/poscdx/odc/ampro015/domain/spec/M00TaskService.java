package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;

import java.util.List;

public interface M00TaskService {
    M00Task find(M00TaskId id);

    List<M00Task> findAll();

    void modify(List<M00Task> entityList);

    M00Task register(M00Task entity);

    void remove(M00TaskId id);
}
