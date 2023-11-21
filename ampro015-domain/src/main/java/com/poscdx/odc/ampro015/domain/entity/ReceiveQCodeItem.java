package com.poscdx.odc.ampro015.domain.entity;

import com.poscoict.base.share.domain.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ReceiveQCodeItem implements JsonSerializable {
    //
    private String transactionCode;             // TransactionCode
    private String worksCode;                   // 사소구분
    private String operFlag;                    // 조업구분
    private String facOpCdN;                    // 공장공정코드
    private String sndrInformEditDate;          // 송신측정보편성일시
    private String sndrInformEditPgmId;         // 송신측정보편성ProgramID
    private String eaiInterfaceId;              // EAI송수신관리InterfaceID
    private String interfaceDataDirActualType;  // InterfaceData지시실적구분
    private String interfaceDataOcrResFlag;     // InterfaceData발생응답구분
    private BigDecimal interfaceDataSendSeq;    // InterfaceData송신순서
    private String interfaceDataUpdTp;          // InterfaceData수정구분
    private BigDecimal interfaceDataTLen;       // InterfaceData총길이
    private String attribute;                   // 여분항목
    private String bscGwDataAttr;               // BSC_GW_Data공통전문내용
    private String itComEaiIfcVarItemUsgF;      // IT공통EAI_Interface가변항목사용유무
    //
    private String itemNum;             // 구매Item코드
    private String description;         // Item코드설명
    private String description1;        // Item코드설명1
    private String mesUnitOfMeasure;    // MES_UOM
    private String listPricePerUnit;    // 구매Item목록가격
    private String ctgyName;            // CategoryName
    private BigDecimal purMatItemWgt;   // 구매자재Item중량
    private String itemTypeGroupName;   // 품종그룹명
}