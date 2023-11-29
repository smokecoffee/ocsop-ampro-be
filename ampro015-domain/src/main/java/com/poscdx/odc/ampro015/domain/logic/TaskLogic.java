package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Task;
import com.poscdx.odc.ampro015.domain.entity.TaskId;
import com.poscdx.odc.ampro015.domain.spec.TaskService;
import com.poscdx.odc.ampro015.domain.store.TaskStore;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskLogic implements TaskService {
    private final TaskStore store;

    public TaskLogic(TaskStore store) {
        this.store = store;
    }

    @Override
    public Task find(String projectNumber, String taskName) {
        TaskId taskId = new TaskId(projectNumber, taskName);
        return store.retrieve(taskId);
    }

    @Override
    public List<Task> findAll(String projectNumber) {
        return store.retrieveList(projectNumber);
    }

    @Override
    public Task modify(Task task) {
        Task existedTask = find(task.getProjectNumber(), task.getTaskName());
        if (Objects.nonNull(existedTask)) {
            existedTask.setCategory(task.getCategory());
            existedTask.setTaskName(task.getTaskName());
            existedTask.setProjectNumber(task.getProjectNumber());
            existedTask.setTaskExplain(task.getTaskExplain());
            existedTask.setEmpId(task.getEmpId());
            existedTask.setLastUpdateId(task.getLastUpdateId());
            existedTask.setLastUpdateTimestamp(Date.from(Instant.now()));
            existedTask.setStatus(task.getStatus());
            return store.update(existedTask);
        }
        return null;
    }

    @Override
    public Task register(Task entity) {
        return store.create(entity);
    }

    @Override
    public void remove(String projectNumber, String taskName) {
        store.delete(projectNumber, taskName);
    }
}
