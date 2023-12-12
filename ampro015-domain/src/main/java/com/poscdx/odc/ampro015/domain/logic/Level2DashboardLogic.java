package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.entity.Pme00Dashboard;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2DashboardService;

import java.util.List;

public class Level2DashboardLogic implements Level2DashboardService {
    @Override
    public Pme00Dashboard loadDashboard(ServiceLifecycle serviceLifecycle) {
        List<Pme00Announcement> announcements = serviceLifecycle.requestPme00AnnouncementService().findAll();
        List<Pme00Meeting> meetings = serviceLifecycle.requestPme00MeetingService().findAll();
        List<Pme00ProjectInfo> projectInfoList = serviceLifecycle.requestPme00ProjectInfoService().findAll();
        Pme00Dashboard dashboard = new Pme00Dashboard();
        dashboard.setAnnouncements(announcements);
        dashboard.setMeetings(meetings);
        dashboard.setProjectInfoList(projectInfoList);
        return dashboard;
    }
}
