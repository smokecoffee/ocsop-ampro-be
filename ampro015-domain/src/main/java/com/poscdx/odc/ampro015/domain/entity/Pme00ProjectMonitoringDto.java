package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00ProjectMonitoringDto {

    private Pme00ProjectInfo projectInfo;

    private List<M00Task> taskList;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00ProjectMonitoringDto fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00ProjectMonitoringDto.class);
    }
}
