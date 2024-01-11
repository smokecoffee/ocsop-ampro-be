package com.poscdx.odc.ampro015.domain.entity;

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
public class M00TaskDto {
    private M00Task task;
    private List<Pme00EmployeeTask> members;
    private EmployeeDto creatorDto;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static M00TaskDto fromJson(String json) {
        return JsonUtil.fromJson(json, M00TaskDto.class);
    }
}
