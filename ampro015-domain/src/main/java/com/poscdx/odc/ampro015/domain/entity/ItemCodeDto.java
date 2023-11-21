package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCodeDto {

    private String itemNum;               // ItemCode
    private String itemNum2;              // ItemCode (QR-Code 의 S Code)
    private String poNum;                 // 구매계약번호 (S-Code)
    private BigDecimal poLineNum;         // 구매계약LINE번호 (S-Code)
    private String description1;          // 품명
    private String description2;          // 작업명
    private String mesUnitOfMeasure;      // 단위
    private BigDecimal purMatItemWgt;     // 단중
    private BigDecimal listPricePerUnit;  // 단가

    private BigDecimal listPricePerUnit1; // ERP 단가
    private BigDecimal listPricePerUnit2; // 수정단가 (Q/S Code: listPricePerUnit, QR: X)
    private Timestamp dataReceivedDate1;  // ERP 수신일자
    private Timestamp dataReceivedDate2;  // 수정일자
    private String rftVesEqpCttId;        // 수정자 직번
    private String rftVesEqpCttNm;        // 수정자 이름
    private String rftReftUpdUpriUsgF;    // 노재수정단가사용유무
    private String deleteFlag;            // 삭제여부
    private String virtualItemCodeF;      // QR 코드 여부 (Y : QR코드)

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static ItemCodeDto fromJson(String json) {
        return JsonUtil.fromJson(json, ItemCodeDto.class);
    }
}