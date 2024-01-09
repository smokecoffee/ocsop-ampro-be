package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Announcement {
    private int id;
    private String name;
    private Date date;
    private int type;
    private String creatorId;
    private String description;
    private String status;
    private String creatorName;
    private String avatar;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Announcement fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Announcement.class);
    }
}
