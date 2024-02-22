package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetSearch {
    private int assetId;
    private String empId;
    private int status;

}
