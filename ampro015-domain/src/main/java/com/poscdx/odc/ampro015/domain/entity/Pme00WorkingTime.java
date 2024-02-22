package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Pme00WorkingTime {
    private int seq;
    private String empId;
    private Date registerDate;
    private Date arrive;
    private Date leave;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;

}
