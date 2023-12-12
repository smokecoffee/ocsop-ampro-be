package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2DashboardService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Level2DashboardLogic implements Level2DashboardService {
    @Override
    public Pme00Dashboard loadDashboard(ServiceLifecycle serviceLifecycle) {

        // Get all announcements
        List<Pme00Announcement> announcements = serviceLifecycle.requestPme00AnnouncementService().findAll();

        // Get all meetings
        List<Pme00Meeting> meetings = serviceLifecycle.requestPme00MeetingService().findAll();

        // Create list projects dto
        List<Pme00ProjectInfo> projectInfoList = serviceLifecycle.requestPme00ProjectInfoService().findAll();
        List<Pme00Member> memberList = serviceLifecycle.requestPme00MemberService().findAll();
        List<Pme00EmployeeTask> employeeTaskList = serviceLifecycle.requestPme00EmployeeTaskService().findAll();

        // get code020
//        List<M00Codes020> codes020List;

        // get code 030
        List<M00Codes030> codes030List = serviceLifecycle.requestM00Codes030Service().findAll();

        // get task
        List<M00Task> taskList = serviceLifecycle.requestTaskService().findAll();

        // get employee meeting
        List<Pme00EmployeeMeeting> employeeMeetingList = serviceLifecycle.requestPme00EmployeeMeetingService().findAll();


        Pme00Dashboard dashboard = new Pme00Dashboard();
        dashboard.setAnnouncementList(announcements);
        dashboard.setMeetingList(meetings);
        dashboard.setProjectInfoList(projectInfoList);
        dashboard.setMemberList(memberList);
        dashboard.setEmployeeTaskList(employeeTaskList);
        dashboard.setCodes030List(codes030List);
        dashboard.setTaskList(taskList);
        dashboard.setEmployeeMeetingList(employeeMeetingList);
        return dashboard;
    }
}
