package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Dashboard {
    private List<Pme00Announcement> announcementList;
    private List<Pme00Meeting> meetingList;
    private List<Pme00ProjectInfo> projectInfoList;
    private List<Pme00Member> memberList;
    private List<Pme00EmployeeTask> employeeTaskList;
//    private List<M00Codes020> codes020List;
    private List<M00Codes030> codes030List;
    private List<M00Task> taskList;
    private List<Pme00EmployeeMeeting> employeeMeetingList;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Dashboard fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Dashboard.class);
    }
}
