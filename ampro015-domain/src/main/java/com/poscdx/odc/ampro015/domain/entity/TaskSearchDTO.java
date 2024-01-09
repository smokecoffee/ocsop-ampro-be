package com.poscdx.odc.ampro015.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchDTO {
    private String projectNumber;
    private String taskName;
    private String empId;
    private String status;
    private String category;
    private Date actualFrom;
    private Date actualTo;
    private Date planFrom;
    private Date planTo;
    private Date startDateFrom;
    private Date startDateTo;
}
