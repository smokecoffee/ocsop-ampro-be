package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Password;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "Pme00Password")
@Table(name = "TB_PME00_RESET_PASSWORD", schema = "POSCTZN")
public class Pme00PasswordJpo
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
    private int expire;
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;

    public Pme00PasswordJpo() {
    }

    public Pme00PasswordJpo(Pme00Password entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Pme00Password toDomain() {
        Pme00Password retVal = new Pme00Password();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<Pme00Password> toDomains(Iterable<Pme00PasswordJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((Pme00PasswordJpo::toDomain)).collect(Collectors.toList());
    }
}
