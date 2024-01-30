package com.poscdx.odc.ampro015.domain.entity.payload.request;

import com.poscdx.odc.ampro015.domain.entity.Pme00Employee;
import lombok.Data;

import java.util.List;

@Data
public class Pme00EmployeesRequest {
    List<Pme00Employee> pme00Employees;
}
