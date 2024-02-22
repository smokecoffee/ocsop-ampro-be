package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00EmployeeTask {

    private String projectNumber;
    private String taskName;
    private String empId;
    private String empName;
    private String avatar;


    public Pme00EmployeeTask(Object[] obj) {
        this.projectNumber = (String) obj[0];
        this.taskName = (String) obj[1];
        this.empId = (String) obj[2];
        this.empName = (String) obj[3];
        this.avatar = (String) obj[4];
    }
}
