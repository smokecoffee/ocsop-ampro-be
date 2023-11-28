package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Task;
import com.poscdx.odc.ampro015.domain.entity.TaskId;

import java.util.List;

public interface TaskStore {
    Task retrieve(TaskId taskId);

    Task update(Task entity);

    Task create(Task entity);

    void delete(String projectId, String taskName);

    public List<Task> retrieveList(String projectNumber);
}
