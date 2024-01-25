package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
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
}
