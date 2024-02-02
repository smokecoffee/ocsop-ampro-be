package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class M00Employee {

    private String empId;
    private String siteCode;
    private String name;
    private String joinDate;
    private String birthDate;
    private String mail;
    private String personalMail;
    private String ipAddress;
    private String mobile;
    private String phone;
    private String address;
    private String avatar;
    private String assetNumberPC;
    private String assetNumberPCInstallDate;
    private String serialNumberPC;
    private String assetNumberMonitor1;
    private String serialNumberMonitor1;
    private String assetNumberMonitor2;
    private String serialNumberMonitor2;
    private String assetNumberPhone;
    private Date creationTimestamp;
    private Date lastUpdateTimestamp;
    private String password;
    private String role;
    private String initEmpId;
    private Date endDate;
    private String empStatus;
    private String createBy;
    private String gender;
    private Integer startWorkingTime;
    public M00Employee(Object[] objects) {
        this.empId = (String) objects[0];
        this.name = (String) objects[1];
        this.avatar = Utils.applyEmployeeAvatarPath((String) objects[2], "Employee");
        this.birthDate = (String) objects[3];
        this.ipAddress = (String) objects[4];
        if(objects.length>5)// process for case get emp by email. need to investigate function more than flexible
            this.mail = (String) objects[5];
    }
}
