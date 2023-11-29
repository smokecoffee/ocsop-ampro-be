package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Task;
import com.poscdx.odc.ampro015.domain.entity.TaskId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Task")
@Table(name = "TB_PME00_TASK", schema = "POSCTZN")
@IdClass(TaskId.class)
public class Pme00TaskJpo implements Serializable {
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
    private Date createBy;

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

    public Pme00TaskJpo(Task domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Task toDomain() {
        Task domainEntity = new Task();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Task> toDomains(Iterable<Pme00TaskJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00TaskJpo::toDomain).collect(Collectors.toList());
    }

}
