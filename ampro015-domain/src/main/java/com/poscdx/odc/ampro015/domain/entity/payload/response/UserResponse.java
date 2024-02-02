package com.poscdx.odc.ampro015.domain.entity.payload.response;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer code;
    private List<M00Employee> data;
    private String message;
}
