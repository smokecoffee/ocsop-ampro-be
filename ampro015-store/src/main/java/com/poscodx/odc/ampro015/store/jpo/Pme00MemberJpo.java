package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00Member")
@Table(name = "TB_PME00_MEMBER", schema = "VIVA-ODC")
public class Pme00MemberJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private int memberId;

    @Column(name = "CD_V_ID")
    private String cdVId;

    @Column(name = "EMP_ID")
    private String empId;

//    @Column(name = "EMP_NAME")
//    private String empName;
//
//    @Column(name = "AVATAR")
//    private String avatar;

    public Pme00MemberJpo(Pme00Member domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00Member toDomain() {
        Pme00Member domainEntity = new Pme00Member();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00Member> toDomains(Iterable<Pme00MemberJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00MemberJpo::toDomain).collect(Collectors.toList());
    }

//    @PostLoad
//    public void setAvatar() {
//        if (avatar != null && !avatar.isEmpty()) {
//            avatar = ConstantUtil.applyEmployeeAvatarPath(avatar, "Employee");
//        }
//    }
}
