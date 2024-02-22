package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Task")
@Table(name = "TB_M00_TASK", schema = "VIVA-ODC")
@IdClass(M00TaskId.class)
public class M00TaskJpo implements Serializable {
    @Id
    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;
    @Id
    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_EXPLAIN")
    private String taskExplain;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PLAN_DATE")
    private Date planDate;

    @Column(name = "ACTUAL_END_DATE")
    private Date actualEndDate;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATION_TIMESTAMP")
    private Date creationTimestamp;

    @Column(name = "LAST_UPDATE_TIMESTAMP")
    private Date lastUpdateTimestamp;

    @Column(name = "LAST_UPDATE_ID")
    private String lastUpdateId;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "STATUS_APPROVE")
    private String statusApprove;

    @Column(name = "ATTACH_FILE")
    private String attach_File;

    @Column(name = "ESTIMATE_DAY")
    private Integer estimate_Day;

    public M00TaskJpo(M00Task domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M00Task toDomain() {
        M00Task domainEntity = new M00Task();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M00Task> toDomains(Iterable<M00TaskJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M00TaskJpo::toDomain).collect(Collectors.toList());
    }

    public static String decodePasswordByBase64(String requestPassword) {
        return new String(Base64.getDecoder().decode(StringUtils.defaultIfBlank(requestPassword, "")));
    }

}