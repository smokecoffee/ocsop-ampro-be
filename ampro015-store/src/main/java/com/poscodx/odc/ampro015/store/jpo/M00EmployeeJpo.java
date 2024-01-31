package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "M00Employee")
@Table(name = "TB_M00_Employee", schema = "POSCTZN")
public class M00EmployeeJpo implements Serializable {

    @Id
    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "SITE_CODE")
    private String siteCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JOIN_DATE")
    private String joinDate;

    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "PERSONAL_MAIL")
    private String personalMail;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHOTO")
    private String avatar;

    @Column(name = "ASSET_NUMBER_PC")
    private String assetNumberPC;

    @Column(name = "ASSET_NUMBER_PC_INSTALL_DATE")
    private String assetNumberPCInstallDate;

    @Column(name = "SERIAL_NO_PC")
    private String serialNumberPC;

    @Column(name = "ASSET_NUMBER_MONITOR1")
    private String assetNumberMonitor1;

    @Column(name = "SERIAL_NO_MONITOR1")
    private String serialNumberMonitor1;

    @Column(name = "ASSET_NUMBER_MONITOR2")
    private String assetNumberMonitor2;

    @Column(name = "SERIAL_NO_MONITOR2")
    private String serialNumberMonitor2;

    @Column(name = "ASSET_NUMBER_PHONE")
    private String assetNumberPhone;

    @Column(name = "CREATION_TIMESTAMP")
    private Date creationTimestamp;

    @Column(name = "LAST_UPDATE_TIMESTAMP")
    private Date lastUpdateTimestamp;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "INIT_EMP_ID")
    private String initEmpId;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "EMP_STATUS")
    private String empStatus;
    @Column(name="CREATE_BY")
    private String createBy;

    @Column(name="GENDER")
    private String gender;

    @Column(name="START_WORKING_TIME")
    private Integer startWorkingTime;

    public M00EmployeeJpo(M00Employee domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }
    public M00EmployeeJpo(Pme00Employee domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M00Employee toDomain() {
        M00Employee domainEntity = new M00Employee();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public Pme00Employee toDomain2() {
        Pme00Employee domainEntity = new Pme00Employee();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M00Employee> toDomains(Iterable<M00EmployeeJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M00EmployeeJpo::toDomain).collect(Collectors.toList());
    }

    public static List<Pme00Employee> toDomains2(Iterable<M00EmployeeJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M00EmployeeJpo::toDomain2).collect(Collectors.toList());
    }

    public static String decodePasswordByBase64(String requestPassword) {
        return new String(Base64.getDecoder().decode(StringUtils.defaultIfBlank(requestPassword, "")));
    }
}
