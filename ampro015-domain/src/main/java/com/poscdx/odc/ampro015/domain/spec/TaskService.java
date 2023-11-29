package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Task;

import java.util.List;

public interface TaskService {
    Task find(String projectNumber, String taskName);

    List<Task> findAll(String projectNumber);

    Task modify(Task Task);

    Task register(Task entity);

    void remove(String projectNumber, String taskName);
}
