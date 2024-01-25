package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
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
@Entity(name = "Pme00EmployeeMeeting")
@Table(name = "TB_PME00_EMPLOYEE_MEETING", schema = "POSCTZN")
public class Pme00EmployeeMeetingJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_MEET_ID")
    private int empMeetId;

    @Column(name = "MEETING_ID")
    private int meetingId;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "AVATAR")
    private String avatar;

    public Pme00EmployeeMeetingJpo(Pme00EmployeeMeeting domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00EmployeeMeeting toDomain() {
        Pme00EmployeeMeeting domainEntity = new Pme00EmployeeMeeting();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00EmployeeMeeting> toDomains(Iterable<Pme00EmployeeMeetingJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00EmployeeMeetingJpo::toDomain).collect(Collectors.toList());
    }

    @PostLoad
    public void setAvatar() {
        if (avatar != null && !avatar.isEmpty()) {
            avatar = ConstantUtil.applyEmployeeAvatarPath(avatar, "Employee");
        }
    }
}
