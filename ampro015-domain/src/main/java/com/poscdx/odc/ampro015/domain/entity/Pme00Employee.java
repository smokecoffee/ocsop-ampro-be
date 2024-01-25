package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pme00Employee {

    private String avatar;
    private String empId;
    private String name;
    private String site;
    private String siteCode;
    private String birthDate;
    private String joinDate;
    private String email;
    private String personalMail;
    private String password;
    private String mobile;
    private String address;
    private String status;
    private String action;
    private String createBy;
    private String createByUserName;
    private String createByUserPhoto;
    private List<Pme00RoleUser> listRoleUser;
    private String nameCreateBy;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Employee fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Employee.class);
    }

    public Pme00Employee(Object[] object) {
        this.avatar = (object[11] == null || ((String) object[11]).isEmpty()) ? null :
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/Employee/" + object[11];
        this.empId = (String) object[0];
        this.name = (String) object[2];
        this.site = (String) object[30];
        this.siteCode = (String) object[1];
        this.birthDate = (String) object[4];
        this.joinDate = (String) object[3];
        this.email = (String) object[5];
        this.personalMail = (String) object[6];
        this.mobile = (String) object[8];
        this.address = (String) object[10];
        this.status = (String) object[26];
        this.action = (String) object[26];
        this.createBy = (String) object[27];
        this.createByUserName = (String) object[28];
        this.createByUserPhoto = (object[29]== null ||((String) object[29]).isEmpty())? null:
                ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/Employee/" +object[29];

    }
}
