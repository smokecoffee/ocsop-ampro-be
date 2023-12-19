package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingOrderDto {
    private int index;
    private int type;
    private Pme00DashboardSetting dashboardSetting;
    private List<Pme00Announcement> announcementList;
    private List<Pme00Meeting> meetingList;
    private ProjectManagementDto projectDto;

}
