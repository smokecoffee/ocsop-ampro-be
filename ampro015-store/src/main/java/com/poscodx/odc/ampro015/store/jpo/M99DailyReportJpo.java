package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.M99DailyReport;
import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "M99DailyReport")
@Table(name = "TB_M99_DAILY_REPORT", schema = "VIVA-ODC")
public class M99DailyReportJpo implements Serializable {

    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    @Column(name = "REGISTER_DATE")
    private Date registerDate;
    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;
    @Column(name = "CONTENTS")
    private String contents;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "ATTC_FILE_NM")
    private String fileName;
    @Column(name = "OP_SAT_DT")
    private Date opSatDate;
    @Column(name = "OP_DN_DT")
    private Date opDnDate;
    @Column(name = "CREATION_TIMESTAMP")
    private Date creationTimestamp;
    @Column(name = "LAST_UPDATE_TIMESTAMP")
    private Date lastUpdateTimestamp;
    @Column(name = "STATUS")
    private int status;

    public M99DailyReportJpo(M99DailyReport domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public M99DailyReport toDomain() {
        M99DailyReport domainEntity = new M99DailyReport();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<M99DailyReport> toDomains(Iterable<M99DailyReportJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(M99DailyReportJpo::toDomain).collect(Collectors.toList());
    }

    @PostLoad
    private void addUrlPath() {
        if (fileName != null && !fileName.isEmpty() && !fileName.contains(Utils.UPLOAD_URL)) {
            fileName = Utils.applyEmployeeAvatarPath(fileName, "Report");
        }
    }

}
