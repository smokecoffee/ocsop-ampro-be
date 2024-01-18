package com.poscodx.odc.ampro015.store.jpo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class IssueManagementIdJpo implements Serializable {
    private int seq;
    private String site;
}
