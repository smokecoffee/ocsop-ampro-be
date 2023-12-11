package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Dashboard {
    private List<Pme00Announcement> announcements;
    private List<Pme00Meeting> meetings;
    private List<Pme00ProjectInfo> projectInfoList;

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Pme00Dashboard fromJson(String json) {
        return JsonUtil.fromJson(json, Pme00Dashboard.class);
    }
}
