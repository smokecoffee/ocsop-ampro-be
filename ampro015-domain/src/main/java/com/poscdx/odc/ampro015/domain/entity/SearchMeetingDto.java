package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchMeetingDto {
    private int cd_tp_id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String creatorId;
    private String requesterId;
    private String categoryMeeting;
    private String status;
    private List<Pme00EmployeeMeeting> listMember;

}
    
