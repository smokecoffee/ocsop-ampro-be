package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.ExportExcel;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level2Logic implements Level2Service {

    @Override
    public String renderQRcode(String token) {
        QRCodeRender qrCodeRender = new QRCodeRender();
        return qrCodeRender.generateEmbeddedQRCodenBase64(token);

    }

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
    public List<AssetInfoDto> findAssetList(ServiceLifecycle serviceLifecycle,String owner, int status) {
        List<Asset> assetList = new ArrayList<>(serviceLifecycle.requestAssetService().findAssetInfos(owner, status));
        List<AssetInfoDto>  result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(assetList)){
            for (Asset asset: assetList){
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
        List<AssetInfoDto>  result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(assetList)){
            for (Asset asset: assetList){
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

}
