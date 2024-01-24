package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Pme00Password
{
    private int id;
    private String empId;
    private String token;
    private int expire;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
}
