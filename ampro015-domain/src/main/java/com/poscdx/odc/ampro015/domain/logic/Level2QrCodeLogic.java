package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2QrCodeService;
import com.poscdx.odc.ampro015.domain.utils.ExportExcel;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class Level2QrCodeLogic implements Level2QrCodeService {

    /**
     * @param serviceLifecycle ServiceLifecycle
     * @param assetInfoDto     AssetInfoDto
     */
    @Override
    public void updateAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto assetInfoDto) {

        //Update asset entity
        Asset asset = assetInfoDto.getAsset();
        serviceLifecycle.requestAssetService().modify(asset);

        //Update list field
        List<Field> fields = assetInfoDto.getFields();
        for (Field field : fields) {
            serviceLifecycle.requestFieldService().modify(field);
        }

        //Update list image
        List<Image> images = assetInfoDto.getImages();
        for (Image image : images) {
            serviceLifecycle.requestImageService().modify(image);
        }

    }

    @Override
    public List<AssetInfoDto> findAssetList(ServiceLifecycle serviceLifecycle, String owner, int status) {
        List<Asset> assetList = new ArrayList<>(serviceLifecycle.requestAssetService().findAssetInfos(owner, status));
        List<AssetInfoDto> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(assetList)) {
            for (Asset asset : assetList) {
                AssetInfoDto item = new AssetInfoDto();
                item.setAsset(asset);
                item.setImages(serviceLifecycle.requestImageService().findImageInfos(asset.getId()));
                item.setFields(serviceLifecycle.requestFieldService().findFieldInfos(asset.getId()));
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void exportExcel(ServiceLifecycle serviceLifecycle, HttpServletResponse response, AssetSearch assetSearch) throws IOException {
        List<Asset> assetList = new ArrayList<>(serviceLifecycle.requestAssetService().findAssetInfos(assetSearch.getOwner(), assetSearch.getStatus()));
        List<AssetInfoDto> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(assetList)) {
            for (Asset asset : assetList) {
                AssetInfoDto item = new AssetInfoDto();
                item.setAsset(asset);
                item.setImages(serviceLifecycle.requestImageService().findImageInfos(asset.getId()));
                item.setFields(serviceLifecycle.requestFieldService().findFieldInfos(asset.getId()));
                result.add(item);
            }
        }
        ExportExcel exportExcel = new ExportExcel(result);
        exportExcel.export(response);
    }

    /**
     * @param serviceLifecycle ServiceLifecycle
     * @param token            String
     * @return
     */
    @Override
    public AssetInfoDto getAsset(ServiceLifecycle serviceLifecycle, String token) {
        AssetInfoDto assetInfoDto = new AssetInfoDto();
        if (StringUtils.isNotBlank(token)) {
            Asset asset = serviceLifecycle.requestAssetService().findByToken(token);
            if (Objects.nonNull(asset)) {
                List<Field> fields = serviceLifecycle.requestFieldService().findByAssetId(asset.getId());
                List<Image> images = serviceLifecycle.requestImageService().findByAssetId(asset.getId());

                assetInfoDto.setAsset(asset);
                assetInfoDto.setImages(images);
                assetInfoDto.setFields(fields);
                return assetInfoDto;
            }
        }
        return assetInfoDto;
    }

    /**
     * @param serviceLifecycle serviceLifecycle
     * @param token            String
     * @param userId           int
     */
    @Override
    public void deleteAsset(ServiceLifecycle serviceLifecycle, String token, int userId) {
        if (StringUtils.isNotBlank(token)) {
            Asset asset = serviceLifecycle.requestAssetService().findByToken(token);
            if (Objects.nonNull(asset)) {
                asset.setDeleteAt(new Date());
                asset.setUpdateAt(new Date());
                asset.setUpdateBy(userId);
                serviceLifecycle.requestAssetService().modify(asset);

                List<Field> fields = serviceLifecycle.requestFieldService().findByAssetId(asset.getId());
                for (Field field : fields) {
                    field.setDeleteAt(new Date());
                    field.setUpdateAt(new Date());
                    field.setUpdateBy(userId);
                    serviceLifecycle.requestFieldService()
                            .modify(field);
                }

                List<Image> images = serviceLifecycle.requestImageService().findByAssetId(asset.getId());
                for (Image image : images) {
                    image.setDeleteAt(new Date());
                    image.setUpdateAt(new Date());
                    image.setUpdateBy(userId);
                    serviceLifecycle.requestImageService().modify(image);
                }
            }
        }
    }

    /**
     * createAsset
     *
     * @param serviceLifecycle ServiceLifecycle
     * @param request          AssetInfoDto
     * @return
     * @author 202293 - Trieu Le
     */
    @Override
    public ResponseEntity<?> createAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto request) {

        ResponseEntity<?> response = null;

        try {
            // Create the new asset
            Asset asset = request.getAsset();
            String tokenString = UUID.randomUUID().toString();
            asset.setToken(tokenString);
            QRCodeRender drCodeRender = new QRCodeRender();
            String qrCode = drCodeRender.generateEmbeddedQRCodenBase64(tokenString);
            asset.setQrcode(qrCode);
            Asset assetAdded = serviceLifecycle.requestAssetService().register(asset);
            int assetId = assetAdded.getId();

            // Create the field list
            List<Field> fields = request.getFields();
            fields.forEach(field -> {
                field.setAssetId(assetId);
                Field filedAdded = serviceLifecycle.requestFieldService().register(field);
            });

            // Create the image list
            List<Image> images = request.getImages();
            images.forEach(image -> {
                image.setAssetId(assetId);
                Image imageAdded = serviceLifecycle.requestImageService().register(image);
            });

            response = new ResponseEntity<>("Successfully", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public byte[] exportQRCode(ServiceLifecycle serviceLifecycle, HttpServletResponse response, String token) throws IOException {
        byte[] imageData = new byte[0];
        if (StringUtils.isNotBlank(token)) {
            Asset asset = serviceLifecycle.requestAssetService().findByToken(token);
            if (Objects.nonNull(asset)) {
                QRCodeRender qrCodeRender = new QRCodeRender();
                imageData = qrCodeRender.exportQRCodeImage(asset);
            }
        }
        return imageData;
    }

}
