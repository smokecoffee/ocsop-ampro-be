package com.poscdx.odc.ampro015.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class M00TaskId implements Serializable {
    private String projectNumber;
    private String taskName;
}
