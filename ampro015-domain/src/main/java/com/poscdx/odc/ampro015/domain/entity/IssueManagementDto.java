package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.tool.schema.spi.ScriptTargetOutput;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IssueManagementDto implements Serializable {
    private IssueManagement issueManagement;
    public IssueManagementDto(Object[] objects){
        issueManagement = new IssueManagement(objects);
    }
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static IssueManagementDto fromJson(String json) {
        return JsonUtil.fromJson(json, IssueManagementDto.class);
    }
}
