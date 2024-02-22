package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00EmployeeMeeting {
    private int empMeetId;
    private int meetingId;
    private String empId;
    private String empName;
    private String avatar;

}
