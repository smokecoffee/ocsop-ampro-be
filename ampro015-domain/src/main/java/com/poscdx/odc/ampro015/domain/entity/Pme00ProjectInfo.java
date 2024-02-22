package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Pme00ProjectInfo
 *
 * @author 202257_Long
 * @since 2023-11-28
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pme00ProjectInfo {
    private String cdV;
    private int period;
    private String koreaPm;
    private String vietnamPl;
    private List<String> searchStatus;
    private String status;
    private String framework;
    private Date startDate;
    private Date endDate;
    private String image;
    private String file;
    private String koreaPmName;
    private String koreaPmImage;
    private String vietnamPlName;
    private String vietnamPlImage;

    public Pme00ProjectInfo(String cdV) {
        this.cdV = cdV;
    }


    public Pme00ProjectInfo(Object[] objects) {

        this.cdV = (String) objects[0];
        this.period = (int) objects[1];
        this.koreaPm = (String) objects[2];
        this.vietnamPl = (String) objects[3];
        this.status = (String) objects[4];
        this.framework = (String) objects[5];
        this.startDate = (Date) objects[6];
        this.endDate = (Date) objects[7];
        this.image = Utils.applyEmployeeAvatarPath((String) objects[8], "Project");
        this.file = Utils.applyEmployeeAvatarPath((String) objects[9], "Project");
        this.koreaPmName = (String) objects[10];
        this.koreaPmImage = Utils.applyEmployeeAvatarPath((String) objects[11], "Employee");
        this.vietnamPlName = (String) objects[12];
        this.vietnamPlImage = Utils.applyEmployeeAvatarPath((String) objects[13], "Employee");
    }
}