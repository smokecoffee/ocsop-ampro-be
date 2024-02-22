package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pme00Meeting {
    private int meetingId;
    private int cd_tp_id;
    private String cdv;
    private String title;
    private Date startTime;
    private Date endTime;
    private String creatorId;
    private String requesterId;
    private String categoryMeeting;
    private String status;
    private String description;
    private List<Pme00EmployeeMeeting> listMember;
    private List<String> empNameList;

}