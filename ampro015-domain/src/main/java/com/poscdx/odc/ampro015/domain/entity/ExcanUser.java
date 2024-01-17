package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ExcanUser
{
    @NotBlank(message = "Id is required")
    @Pattern(message = "Number should have at 6 digits", regexp="^[0-9]{6}$")
//    @Size(min = 6, max = 6, message = "Number should have at 6 digits")
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    private int roleId;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @Pattern(message = "Phone is not valid", regexp="^(84|0[3|5|7|8|9]|02[0-9]?)(([0-9]{8})|([0-9]{1}-([0-9]{3})-[0-9]{4}))$")
    private String phone;
    private String address;
    private String avatar;
    private String desc;
    private int status;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
    private Date deleteAt;
}
