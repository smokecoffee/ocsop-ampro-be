package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity(name = "Pme00Meeting")
@Table(name = "tb_pme00_meeting", schema = "POSCTZN")
public class Pme00MeetingJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private int meetingId;

    @Column(name = "CD_TP_ID")
    private int cd_tp_id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "REQUESTER_ID")
    private String requesterId;

    @Column(name = "CATEGORY_MEETING")
    private String categoryMeeting;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DESCRIPTION")
    private String description;

    public Pme00MeetingJpo(Pme00Meeting domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public Pme00Meeting toDomain() {
        Pme00Meeting domainEntity = new Pme00Meeting();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<Pme00Meeting> toDomains(Iterable<Pme00MeetingJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(Pme00MeetingJpo::toDomain).collect(Collectors.toList());
    }

}
