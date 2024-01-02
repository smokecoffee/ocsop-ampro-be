package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Pme00Announcement")
@Table(name = "TB_PME00_ANNOUNCEMENT", schema = "POSCTZN")
public class Pme00AnnouncementJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "TYPE")
    private int type;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Transient
    private String creatorName;

    public Pme00AnnouncementJpo(Pme00Announcement domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00Announcement toDomain() {
        Pme00Announcement domainEntity = new Pme00Announcement();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00Announcement> toDomains(Iterable<Pme00AnnouncementJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00AnnouncementJpo::toDomain).collect(Collectors.toList());
    }
}
