package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Member {
    private int memberId;
    private String cdVId;
    private String empId;
    private String empName;
    private String avatar;


    public Pme00Member(Object[] objects) {
        this.memberId = (int) objects[0];
        this.cdVId = (String) objects[1];
        this.empId = (String) objects[2];
        this.empName = (String) objects[3];
        this.avatar = Utils.applyEmployeeAvatarPath((String) objects[4], "Employee");
    }
}
