package com.poscdx.odc.ampro015.domain.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMember {
    private M00Task m00Task;
    private List<Pme00EmployeeTask> pme00EmployeeTasks;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static TaskMember fromJson(String json) {
        return JsonUtil.fromJson(json, TaskMember.class);
    }
}
