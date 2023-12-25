package com.poscdx.odc.ampro015.domain.emun;

import java.util.Arrays;
import java.util.Optional;

public enum M00TaskJpoComlumnName {
    TASK_NAME("taskName"),
    PROJECT_NUMBER("projectNumber"),
    TASK_EXPLAIN("taskExplain"),
    EMP_ID("empId"),
    STATUS("status"),
    PLAN_DATE("planDate"),
    ACTUAL_END_DATE("actualEndDate"),
    REMARK("remark"),
    WRITER("writer"),
    PASSWORD("password"),
    CATEGORY("category"),
    LAST_UPDATE_TIMESTAMP("lastUpdateTimestamp");


    M00TaskJpoComlumnName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public static Optional<M00TaskJpoComlumnName> getColumnName(String fieldName){
        return Arrays.stream(M00TaskJpoComlumnName.values()).filter(m00TaskJpoComlumnName -> m00TaskJpoComlumnName.getFieldName().equals(fieldName))
                .findFirst();
    }
}