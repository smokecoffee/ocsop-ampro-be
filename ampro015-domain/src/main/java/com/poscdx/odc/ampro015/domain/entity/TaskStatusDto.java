package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusDto{

    private String cdV;
    private String cdVMeaning;
    private String cdVColor;

    public TaskStatusDto(Object[] objects) {
        this.cdV = (String) objects[0];
        this.cdVMeaning = (String) objects[1];
        this.cdVColor = (String) objects[2];
    }
}
