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

    public EmployeeDto(Object[] objects) {
        this.empId = (String) objects[0];
        this.name = (String) objects[1];
    }
}
