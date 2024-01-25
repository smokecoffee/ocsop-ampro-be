package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Pme00PasswordToken
{
    private int id;
    private String empId;
    private String token;
    private long expire;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;

    public Pme00PasswordToken(Object[] objects) {
        this.id = (int)objects[0];
        this.empId = (String)objects[1];
        this.token = (String)objects[2];
        this.expire = ((BigInteger)objects[3]).longValue();
        this.createAt = (Date) objects[4];
        this.createBy = (String)objects[5];
        this.updateAt = (Date)objects[6];
        this.updateBy = (String)objects[7];
        //ID, EMPLOYEE_ID, TOKEN, EXPIRE, CREATE_AT, CREATE_BY, UPDATE_AT, UPDATE_BY
    }

    public boolean IsExpired() {
        long currentTimeInMillis = System.currentTimeMillis();
        return this.expire < currentTimeInMillis;
    }
}
