package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class AssetInfoDto {
    private Asset asset;
    private List<Field> fields;
    private List<Image> images;
}
