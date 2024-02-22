package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "Pme00Password")
@Table(name = "TB_PME00_RESET_PASSWORD", schema = "VIVA-ODC")
public class Pme00PasswordTokenJpo
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "EMPLOYEE_ID")
    private String empId;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "EXPIRE")
    private long expire;
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;

    public Pme00PasswordTokenJpo() {
    }

    public Pme00PasswordTokenJpo(Pme00PasswordToken entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Pme00PasswordToken toDomain() {
        Pme00PasswordToken retVal = new Pme00PasswordToken();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<Pme00PasswordToken> toDomains(Iterable<Pme00PasswordTokenJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((Pme00PasswordTokenJpo::toDomain)).collect(Collectors.toList());
    }
}
