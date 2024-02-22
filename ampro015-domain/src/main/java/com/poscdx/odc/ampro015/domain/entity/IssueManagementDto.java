package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class IssueManagementDto implements Serializable {
    private IssueManagement issueManagement;

    public IssueManagementDto(Object[] objects) {
        issueManagement = new IssueManagement(objects);
    }

}
