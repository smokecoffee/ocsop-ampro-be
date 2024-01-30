package com.poscdx.odc.ampro015.domain.entity;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class S91Menu {

    private String category;
    private String formMenuExplain;
    private String url;

}
