package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
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

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00EmployeeMeeting fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00EmployeeMeeting.class);
    }
}
