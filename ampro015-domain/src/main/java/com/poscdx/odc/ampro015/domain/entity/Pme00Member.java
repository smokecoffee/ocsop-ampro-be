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
public class Pme00Member {
    private int memberId;
    private String cdVId;
    private String empId;
    private String empName;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Member fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Member.class);
    }

    public Pme00Member(Object[] objects) {
        this.memberId = (int) objects[0];
        this.cdVId = (String) objects[1];
        this.empId = (String) objects[2];
        this.empName = (String) objects[3];
    }
}
