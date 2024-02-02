package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Project list Dto
 *
 * @author : 202301_Duyen
 * @since : 2023-12-12
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManagementDto {

    private M00Codes030 m00Codes030;
    private Pme00ProjectInfo pme00ProjectInfo;
    private List<Pme00Member> lstMember;
    private int progress;
    private List<M00TaskDto> lstTask;
    private Date fromStartDate;
    private Date toStartDate;
    private Date fromEndDate;
    private Date toEndDate;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static ProjectManagementDto fromJson(String json) {
        return JsonUtil.fromJson(json, ProjectManagementDto.class);
    }

}
