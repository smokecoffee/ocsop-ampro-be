package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.lifecycle.StoreLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import com.poscoict.base.share.domain.NameValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Level2Logic implements Level2Service {

    private static final Logger logger = LoggerFactory.getLogger(Level2Logic.class);

    @Autowired
    StoreLifecycle storeLifecycle;

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
    public String RenderQRcode(String token) {
        QRCodeRender qrCodeRender = new QRCodeRender();
        return qrCodeRender.generateEmbeddedQRCodenBase64(token);
    }

    /**
     * addNewAsset
     * @author 202293 - Trieu Le
     * @since 2023-11-23
     * 
     * @param asset
     * @param fields
     * @param images
     * @return ResponseEntity
     *
     */
    @Override
    public ResponseEntity<?> addNewAsset(Asset asset, List<Field> fields, List<Image> images) {

        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
        try {
            logger.info("Storing asset information into the database");
            String tokenString = UUID.randomUUID().toString();
            asset.setToken(tokenString);
            QRCodeRender drCodeRender = new QRCodeRender();
            String qrCode = drCodeRender.generateEmbeddedQRCodenBase64(tokenString);
            asset.setQrcode(qrCode);
            Asset assetAdded = storeLifecycle.requestAssetStore().add(asset);
            int assetId = assetAdded.getId();
            logger.info("Asset with id {} was added into database: {}", assetId, assetAdded.toString());

            logger.info("Storing field list information into the database");
            fields.forEach(field -> {
                field.setAssetId(assetId);
                Field filedAdded = storeLifecycle.requestFieldStore().add(field);
                logger.info("Field with id {} was added into database: {}", filedAdded.getId(), filedAdded.toString());
            });

            logger.info("Storing image list information into the database");
            images.forEach(image -> {
                image.setAssetId(assetId);
                Image imageAdded = storeLifecycle.requestImageStore().add(image);
                logger.info("Image with id {} was added into database: {}", imageAdded.getId(), imageAdded.toString());
            });

            logger.info("<-------- End processing create new asset with information -------->");
            response = new ResponseEntity<>("Successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception - There is an exception when adding the new asset: {}", e.getMessage());
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}

