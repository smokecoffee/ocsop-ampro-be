package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscoict.base.share.domain.PoscoEntity;
import lombok.*;

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
    private String birthday;
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

    public M00Employee(Object[] objects) {
        this.empId = (String) objects[0];
        this.name = (String) objects[1];
        this.avatar = (objects[2] == null || ((String) objects[2]).isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/Employee/" + objects[2];
        this.birthday = (String) objects[3];
        this.ipAddress = (String) objects[4];
    }
}
