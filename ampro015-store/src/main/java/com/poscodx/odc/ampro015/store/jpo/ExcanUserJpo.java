package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@Entity(name = "ExcanUser")
@Table(name = "TB_EXCAN_USER", schema = "AMPRO")
public class ExcanUserJpo
{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ROLE_ID")
    private int roleId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
//    @NotBlank(message = "Email is required")
//    @NotEmpty(message = "Email cannot be empty")
//    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "AVATAR")
    private String avatar;
    @Column(name = "DESCRIPTION")
    private String desc;
    @Column(name = "STATUS")
    private int status;
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "DELETE_AT")
    private Date deleteAt;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinTable(  name = "TB_EXCAN_ROLE",
//            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable = false)
//    private ExcanRoleJpo excanRoleJpo;

    public ExcanUserJpo() {
    }

    public ExcanUserJpo(ExcanUser entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public ExcanUser toDomain() {
        ExcanUser retVal = new ExcanUser();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<ExcanUser> toDomains(Iterable<ExcanUserJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((ExcanUserJpo::toDomain)).collect(Collectors.toList());
    }

//    @Override
//    public void validateJpo()
//        throws PosBaseException
//    {
//    }
}
