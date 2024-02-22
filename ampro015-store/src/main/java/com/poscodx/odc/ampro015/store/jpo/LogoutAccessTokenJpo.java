package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
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
@Entity(name = "LogoutAccessToken")
@Table(name = "TB_PME00_LOGOUT_ACCESS_TOKEN", schema = "VIVA-ODC")
public class LogoutAccessTokenJpo
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TOKEN")
    private String token;
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

    public LogoutAccessTokenJpo() {
    }

    public LogoutAccessTokenJpo(LogoutAccessToken entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public LogoutAccessToken toDomain() {
        LogoutAccessToken retVal = new LogoutAccessToken();
        BeanUtils.copyProperties(this, retVal);
        return retVal;
    }

    public static List<LogoutAccessToken> toDomains(Iterable<LogoutAccessTokenJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map((LogoutAccessTokenJpo::toDomain)).collect(Collectors.toList());
    }
}
