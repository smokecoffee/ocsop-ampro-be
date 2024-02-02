package com.poscodx.odc.ampro015.service;

import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.ProjectManagementDto;
import com.poscdx.odc.ampro015.domain.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionValidation {

    private static String userId;

    public static boolean validateUpdateProject(ProjectManagementDto dto) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return !Utils.checkPermission("DELETE_PROJECT_OWNER") ||
                dto.getM00Codes030().getCreatedProgramId().equals(userId) ||
                dto.getPme00ProjectInfo().getKoreaPm().equals(userId) ||
                dto.getPme00ProjectInfo().getVietnamPl().equals(userId);
    }

    public static boolean validateDeleteProject(ProjectManagementDto dto) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return !Utils.checkPermission("DELETE_PROJECT_OWNER") ||
                dto.getM00Codes030().getCreatedProgramId().equals(userId) ||
                dto.getPme00ProjectInfo().getKoreaPm().equals(userId) ||
                dto.getPme00ProjectInfo().getVietnamPl().equals(userId);
    }

    public static String validateGetProject() {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return Utils.checkPermission("GET_PROJECT_OWNER") ? userId : null;
    }

    public static String validateGetProjectMonitoring() {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return Utils.checkPermission("GET_PROJECT_OWNER") ? userId : null;
    }

    public static boolean validateUpdateMeeting(List<Pme00Meeting> listMeeting) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        if (Utils.checkPermission("UPDATE_MEETING_OWNER")) {
            List<Pme00Meeting> checkList = listMeeting.stream()
                    .filter(pme00Meeting -> !pme00Meeting.getCreatorId().equals(userId) &&
                                            !pme00Meeting.getRequesterId().equals(userId))
                    .collect(Collectors.toList());
            return checkList.isEmpty();
        }
        return true;
    }

    public static boolean validateDeleteMeeting(Pme00Meeting pme00Meeting) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return (!Utils.checkPermission("DELETE_MEETING_OWNER") ||
                pme00Meeting.getCreatorId().equals(userId) ||
                pme00Meeting.getRequesterId().equals(userId));
    }

//    public static boolean validateUpdateReport(List<Pme00Meeting> listMeeting) {
//        if (userId == null) userId = Utils.getLoginUserDetail();
//        if (Utils.checkPermission("UPDATE_MEETING_OWNER")) {
//            List<Pme00Meeting> checkList = listMeeting.stream()
//                    .filter(pme00Meeting -> !pme00Meeting.getCreatorId().equals(userId) &&
//                            !pme00Meeting.getRequesterId().equals(userId))
//                    .collect(Collectors.toList());
//            return checkList.isEmpty();
//        }
//        return true;
//    }
//
//    public static boolean validateDeleteReport(Pme00Meeting pme00Meeting) {
//        if (userId == null) userId = Utils.getLoginUserDetail();
//        return !Utils.checkPermission("DELETE_MEETING_OWNER") ||
//                pme00Meeting.getCreatorId().equals(userId) ||
//                pme00Meeting.getRequesterId().equals(userId);
//    }

    public static boolean validateUpdateEmployee(Pme00Employee pme00Employee) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return Utils.checkPermission("UPDATE_EMPLOYEE") ||
               pme00Employee.getEmpId().equals(userId);
    }

    public static boolean validateSearchEmployee(String empId) {
        if (userId == null) userId = Utils.getLoginUserDetail();
        return Utils.checkPermission("GET_EMPLOYEE") ||
               (empId != null && empId.equals(userId));
    }
}
