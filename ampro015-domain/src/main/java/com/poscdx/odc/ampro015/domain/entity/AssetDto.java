package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter()
@NoArgsConstructor
public class AssetDto {
    private Asset asset;
    private List<Field> fields;
    private List<Image> images;
}
