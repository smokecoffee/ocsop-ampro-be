package com.poscdx.odc.ampro015.domain.logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level3DashboardService;

import java.util.ArrayList;
import java.util.List;

public class Level3DashboardLogic implements Level3DashboardService {
    @Override
    public Pme00DashboardSettingDto loadDashboard(ServiceLifecycle serviceLifecycle, String empId) {
        Pme00DashboardSettingDto dto = new Pme00DashboardSettingDto();
        Pme00DashboardSetting setting = serviceLifecycle.requestPme00DashboardSettingService().findByEmpId(empId);
        if (setting == null) {
            return null;
        }
        dto.setPme00DashboardSetting(setting);
        JsonArray jsonArray = (JsonArray) JsonParser.parseString(dto.getPme00DashboardSetting().getOrder());
        JsonObject jsonObject;
        SettingOrderDto settingOrderDto;
        List<SettingOrderDto> list = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            jsonObject = (JsonObject) element;
            settingOrderDto = new SettingOrderDto();
            settingOrderDto.setIndex(jsonObject.get("index").getAsInt());
            settingOrderDto.setType(jsonObject.get("type").getAsInt());
            switch (settingOrderDto.getType()) {
                case 1: {
                    settingOrderDto.setAnnouncementList(serviceLifecycle.requestPme00AnnouncementService().findAll());
                    break;
                }
                case 2: {
                    settingOrderDto.setMeetingList(serviceLifecycle.requestBookingMeetingRoomService()
                                                    .getMeetingByEndDate(serviceLifecycle).getListData());
                    break;
                }
                case 3: {
                    String projectNumber = jsonObject.get("projectNumber").getAsString();
                    ProjectManagementDto projectManagementDto = new ProjectManagementDto();
                    projectManagementDto.setPme00ProjectInfo(new Pme00ProjectInfo(projectNumber));
                    projectManagementDto.setM00Codes030(new M00Codes030(projectNumber));
                    System.out.println(projectNumber);
                    System.out.println(projectManagementDto.getPme00ProjectInfo().getCdV());
                    System.out.println(projectManagementDto.getM00Codes030().getCdV());
                    List<ProjectManagementDto> resultList = serviceLifecycle.requestLevel2ProjectService()
                                                            .getProjectList(serviceLifecycle, projectManagementDto);
                    settingOrderDto.setProjectDto(resultList.isEmpty() ? null : resultList.get(0));
                    break;
                }
            }
            list.add(settingOrderDto);
        }
        dto.setSettingOrderDtoList(list);
        return dto;
    }
}
