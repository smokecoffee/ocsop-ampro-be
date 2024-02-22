package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class M00TaskDto {
    private M00Task task;
    private List<Pme00EmployeeTask> members;
    private M00Employee creatorDto;
    private M00Employee pmDto;
    private M00Employee plDto;

}
