package com.poscdx.odc.ampro015.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class TaskId implements Serializable {
    private String projectNumber;
    private String taskName;

    public TaskId(String projectNumber, String taskName) {
        this.projectNumber = projectNumber;
        this.taskName = taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(projectNumber, taskId.projectNumber) && Objects.equals(taskName, taskId.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNumber, taskName);
    }
}
