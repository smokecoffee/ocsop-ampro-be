package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.PoscoEntity;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends PoscoEntity {

    private String empId;
    private String name;
    private String avatar;


    public EmployeeDto(Object[] objects) {
        this.empId = (String) objects[0];
        this.name = (String) objects[1];
        this.avatar = "http://172.25.219.61:8080/img/" + objects[2];
    }
}
