package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00EmployeeTask")
@Table(name = "TB_PME00_EMPLOYEE_TASK", schema = "POSCTZN")
@IdClass(M00EmployeeTaskId.class)
public class Pme00EmployeeTaskJpo {

    @Id
    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;

    @Id
    @Column(name = "TASK_NAME")
    private String taskName;

    @Id
    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "AVATAR")
    private String avatar;

    public Pme00EmployeeTaskJpo(Pme00EmployeeTask domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00EmployeeTask toDomain() {
        Pme00EmployeeTask domainEntity = new Pme00EmployeeTask();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00EmployeeTask> toDomains(Iterable<Pme00EmployeeTaskJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00EmployeeTaskJpo::toDomain).collect(Collectors.toList());
    }

    @PostLoad
    public void setAvatar() {
        if (avatar != null && !avatar.isEmpty()) {
            avatar = ConstantUtil.applyEmployeeAvatarPath(avatar, "Employee");
        }
    }
}
