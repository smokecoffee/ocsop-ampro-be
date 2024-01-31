package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pme00Employee {

    private String avatar;

//    @NotBlank(message = "EmpId is required")
//    @NotEmpty(message = "EmpId cannot be empty")
    private String empId;
//
//    @NotBlank(message = "Name is required")
//    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String site;

//    @NotBlank(message = "SiteCode is required")
//    @NotEmpty(message = "SiteCode cannot be empty")
    private String siteCode;

    private String birthDate;
    private String joinDate;

//    @NotBlank(message = "Mail is required")
//    @NotEmpty(message = "Mail cannot be empty")
    private String mail;

//    @NotBlank(message = "PersonalMail is required")
//    @NotEmpty(message = "PersonalMail cannot be empty")
    private String personalMail;

//    @NotBlank(message = "Password is required")
//    @NotEmpty(message = "Password cannot be empty")
    private String password;
//
//    @NotBlank(message = "Mobile is required")
//    @NotEmpty(message = "Mobile cannot be empty")
    private String mobile;

    private String address;

//    @NotBlank(message = "Status is required")
//    @NotEmpty(message = "status cannot be empty")
    private String status;

    private String action;
    private String createBy;
    private String createByUserName;
    private String createByUserPhoto;
    private List<Pme00RoleUser> listRoleUser;
    private String nameCreateBy;
    private LocalTime startWorkingTime;

//    @NotBlank(message = "Gender is required")
//    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    private String ipAddress;
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Employee fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Employee.class);
    }

    public Pme00Employee(Object[] object) {
        this.avatar = (object[11] == null || ((String) object[11]).isEmpty()) ? null :
                Utils.UPLOAD_URL + Utils.UPLOAD_BUCKET + "/Employee/" + object[11];
        this.empId = (String) object[0];
        this.name = (String) object[2];
        this.site = (String) object[31];
        this.siteCode = (String) object[1];
        this.birthDate = (String) object[4];
        this.joinDate = (String) object[3];
        this.mail = (String) object[5];
        this.personalMail = (String) object[6];
        this.mobile = (String) object[8];
        this.address = (String) object[10];
        this.status = (String) object[32];
        this.action = (String) object[26];
        this.createBy = (String) object[27];
        this.createByUserName = (String) object[29];
        this.createByUserPhoto = (object[30]== null ||((String) object[30]).isEmpty())? null:
                Utils.UPLOAD_URL + Utils.UPLOAD_BUCKET + "/Employee/" +object[30];
        this.gender = (String) object[28];
        this.ipAddress = (String) object[7];

    }
}
