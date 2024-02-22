package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfoDto {
    private Asset asset;
    private List<Field> fields;
    private List<Image> images;

}
