package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscoict.base.share.domain.NameValueList;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Level2Logic implements Level2Service {

    @Override
    public List<ItemCodeDto> findItemCodeInfos(ServiceLifecycle serviceLifecycle, String codeType, String description) {
        List<QCodeItem> qCodeList = new ArrayList<>();
        List<SCodeItem> sCodeList = new ArrayList<>();
        if (ObjectUtils.isEmpty(codeType) || "A".equals(codeType)) {
            qCodeList.addAll(
                    serviceLifecycle.requestQCodeItemService().findAll()
                            .stream()
                            .filter(qCode -> ObjectUtils.isEmpty(qCode.getDeleteFlag()))
                            .filter(qCode -> {
                                String description1 = qCode.getDescription1();
                                String itemCode = qCode.getItemNum();
                                if (ObjectUtils.isEmpty(description))
                                    return true;
                                return (!ObjectUtils.isEmpty(description1) && description1.contains(description)) || itemCode.contains(description);
                            })
                            .collect(Collectors.toList())
            );
        }
        if (ObjectUtils.isEmpty(codeType) || "B".equals(codeType)) {
            sCodeList.addAll(
                    serviceLifecycle.requestSCodeItemService().findAll()
                            .stream()
                            .filter(sCode -> ObjectUtils.isEmpty(sCode.getDeleteFlag()))
                            .filter(sCode -> {
                                String description1 = sCode.getDescription1();
                                String itemCode = sCode.getItemNum();
                                if (ObjectUtils.isEmpty(description))
                                    return true;
                                return (!ObjectUtils.isEmpty(description1) && description1.contains(description)) || itemCode.contains(description);
                            })
                            .collect(Collectors.toList())
            );
        }
        // List
        List<ItemCodeDto> itemCodeList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(qCodeList)) {
            itemCodeList.addAll(
                    qCodeList.stream()
                            .map(qCode -> ItemCodeDto.builder()
                                    .itemNum(qCode.getItemNum())
                                    .description1(qCode.getDescription1())
                                    .description2(qCode.getDescription3())
                                    .mesUnitOfMeasure(qCode.getMesUnitOfMeasure())
                                    .purMatItemWgt(qCode.getPurMatItemWgt())
                                    .listPricePerUnit(qCode.getListPricePerUnit())
                                    .build()
                            )
                            .collect(Collectors.toList())
            );
        }
        if (!ObjectUtils.isEmpty(sCodeList)) {
            itemCodeList.addAll(
                    sCodeList.stream()
                            .map(sCode -> ItemCodeDto.builder()
                                    .itemNum(sCode.getItemNum())
                                    .description1(sCode.getDescription1())
                                    .description2(sCode.getDescription3())
                                    .mesUnitOfMeasure(sCode.getMesUnitOfMeasure())
                                    .listPricePerUnit(sCode.getListPricePerUnit())
                                    .build()
                            )
                            .collect(Collectors.toList())
            );
        }

        return itemCodeList;
    }

    /**
     * modifyItemCodeInfo
     * 내화물 코드 수정
     *
     * @param serviceLifecycle ServiceLifecycle
     * @param itemCodeDtoList  List<ItemCodeDto>
     */
    @Override
    public void modifyItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList) {
        Map<String, List<ItemCodeDto>> codeGroup = itemCodeDtoList.stream()
                .collect(Collectors.groupingBy(dto -> {
                    String itemNum = dto.getItemNum();
                    return "Q".equals(String.valueOf(itemNum.charAt(0))) ? "A" : "B";
                }));
        for (Map.Entry<String, List<ItemCodeDto>> codeGroupEntry : codeGroup.entrySet()) {
            String codeGroupKey = codeGroupEntry.getKey();
            List<ItemCodeDto> group = codeGroupEntry.getValue();
            // A: QCode, B: SCode
            if ("A".equals(codeGroupKey) && !ObjectUtils.isEmpty(group)) {
                List<QCodeItem> qCodeItems = serviceLifecycle.requestQCodeItemService().findAll();
                qCodeItems.forEach(qCodeItem -> {
                    group.forEach(itemCodeDto -> {
                        if (itemCodeDto.getItemNum().equals(qCodeItem.getItemNum())) {
                            serviceLifecycle.requestQCodeItemService()
                                    .modify(itemCodeDto.getItemNum(),
                                            NameValueList.newInstance("description3",
                                                    itemCodeDto.getDescription2()));
                        }
                    });
                });
            }
            if ("B".equals(codeGroupKey) && !ObjectUtils.isEmpty(group)) {
                List<SCodeItem> sCodeItems = serviceLifecycle.requestSCodeItemService().findAll();
                sCodeItems.forEach(sCodeItem -> {
                    group.forEach(itemCodeDto -> {
                        if (itemCodeDto.getItemNum().equals(sCodeItem.getItemNum())) {
                            serviceLifecycle.requestSCodeItemService()
                                    .modify(itemCodeDto.getItemNum(),
                                            sCodeItem.getPoLineNum(),
                                            sCodeItem.getPoNum(),
                                            NameValueList.newInstance("description3",
                                                    itemCodeDto.getDescription2()));
                        }
                    });
                });
            }
        }
    }

    /**
     * deleteItemCodeInfo
     * 내화물 코드 삭제
     *
     * @param serviceLifecycle ServiceLifecycle
     * @param itemCodeDtoList  List<ItemCodeDto>
     */
    @Override
    public void deleteItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList) {
        Map<String, List<ItemCodeDto>> codeGroup = itemCodeDtoList.stream()
                .collect(Collectors.groupingBy(dto -> {
                    String itemNum = dto.getItemNum();
                    return "Q".equals(String.valueOf(itemNum.charAt(0))) ? "A" : "B";
                }));
        for (Map.Entry<String, List<ItemCodeDto>> codeGroupEntry : codeGroup.entrySet()) {
            String codeGroupKey = codeGroupEntry.getKey();
            List<ItemCodeDto> group = codeGroupEntry.getValue();
            // A: QCode, B: SCode
            if ("A".equals(codeGroupKey) && !ObjectUtils.isEmpty(group)) {
                group.forEach(itemCodeDto -> {
                        if (Objects.nonNull(itemCodeDto)) {
                            QCodeItem qCodeItem = serviceLifecycle.requestQCodeItemService().find(itemCodeDto.getItemNum());
                            if (Objects.nonNull(qCodeItem)) {
                                serviceLifecycle.requestQCodeItemService()
                                        .modify(qCodeItem.getItemNum(),
                                                NameValueList.newInstance("deleteFlag", "Y"));
                            }
                        }
                    }
                );
            }
            if ("B".equals(codeGroupKey) && !ObjectUtils.isEmpty(group)) {
                List<SCodeItem> sCodeItems = serviceLifecycle.requestSCodeItemService().findAll();
                sCodeItems.forEach(sCodeItem -> {
                    group.forEach(itemCodeDto -> {
                        if (itemCodeDto.getItemNum().equals(sCodeItem.getItemNum())) {
                            serviceLifecycle.requestSCodeItemService()
                                    .modify(itemCodeDto.getItemNum(),
                                            sCodeItem.getPoLineNum(),
                                            sCodeItem.getPoNum(),
                                            NameValueList.newInstance("deleteFlag", "Y"));
                        }
                    });
                });
            }
        }
    }

    @Override
    public List<AssetDto> findAssetList(ServiceLifecycle serviceLifecycle,String owner, int status) {
        List<Asset> assetList = new ArrayList<>(serviceLifecycle.requestAssetService().findAssetInfos(owner, status));
        List<AssetDto>  result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(assetList)){
            for (Asset asset: assetList){
                AssetDto item = new AssetDto();
                item.setAsset(asset);
                item.setImages(serviceLifecycle.requestImageService().findImageInfos(asset.getId()));
                item.setFields(serviceLifecycle.requestFieldService().findFieldInfos(asset.getId()));
            }
        }
        return result;
    }
}
