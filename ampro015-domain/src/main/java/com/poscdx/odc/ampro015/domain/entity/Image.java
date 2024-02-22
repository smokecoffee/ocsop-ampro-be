package com.poscdx.odc.ampro015.domain.entity;

import com.google.gson.annotations.JsonAdapter;
import com.poscdx.odc.ampro015.domain.utils.DateUtils;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image  {
    private int id;
    private int assetId;
    private String name;
    private String originalName;
    private String path;
    private String createBy;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date createAt;
    private String updateBy;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date updateAt;
    @JsonAdapter(DateUtils.DateTypeAdapter.class)
    private Date deleteAt;
}
