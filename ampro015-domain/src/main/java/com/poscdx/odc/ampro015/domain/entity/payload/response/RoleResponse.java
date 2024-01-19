package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Integer code;
    private List<?> data;
    private String message;
}
