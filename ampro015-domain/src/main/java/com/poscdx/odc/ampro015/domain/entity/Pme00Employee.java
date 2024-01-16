package com.poscdx.odc.ampro015.domain.entity;

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
    private String birthDate;
    private String joinDate;
    private String email;
    private String mobile;
    private String address;
    private String status;
    private String action;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Employee fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Employee.class);
    }

    public Pme00Employee(Object[] object) {
         this.avatar = (String) object[11];
         this.empId = (String) object[0];
         this.name = (String) object[2];
         this.site = (String) object[27];
         this.birthDate = (String) object[4];
         this.joinDate = (String) object[3];
         this.email = (String) object[6];
         this.mobile = (String) object[8];
         this.address = (String) object[10];
         this.status = (String) object[26];
         this.action = (String) object[26];
    }
}
