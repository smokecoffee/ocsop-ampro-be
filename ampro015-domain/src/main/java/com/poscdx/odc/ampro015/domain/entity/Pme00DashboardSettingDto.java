package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00DashboardSettingDto {
    private Pme00DashboardSetting pme00DashboardSetting;
    private List<SettingOrderDto> settingOrderDtoList;

}
