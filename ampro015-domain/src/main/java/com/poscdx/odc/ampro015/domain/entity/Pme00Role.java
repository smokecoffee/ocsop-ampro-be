package com.poscdx.odc.ampro015.domain.entity;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00Role {
    private int id;
    private String name;
    private String description;
    private int status;

}
