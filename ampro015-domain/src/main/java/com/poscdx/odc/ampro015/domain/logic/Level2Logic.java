package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Level2Service;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscdx.odc.ampro015.domain.utils.ExportExcel;
import com.poscdx.odc.ampro015.domain.utils.QRCodeRender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
        Pme00ProjectInfo info = new Pme00ProjectInfo();

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

    /**
     *
     * @param serviceLifecycle
     * @param token
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
     *
     * @param serviceLifecycle
     * @param token
     * @param userId
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
     * @author 202293 - Trieu Le
     *
     * @param serviceLifecycle ServiceLifecycle
     * @param request          AssetInfoDto
     * @return
     *
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
    public Pme00ProjectListDto registerProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto){

        // Insert data M00Codes030
        M00Codes030 entityCodes030 = new M00Codes030();

        int seq = serviceLifecycle.requestM00Codes030Service().getMaxSeqInquiry(ConstantUtil.CD_TP_ID, ConstantUtil.CATEGORY_GROUP_ID) + 1;

        entityCodes030.setCdTpId(dto.getCdTpId());
        entityCodes030.setCategoryGroupId(dto.getCategoryGroupId());
        entityCodes030.setCdV(dto.getCdV());
        entityCodes030.setCdvMeaning(dto.getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getCdVExplain());
        entityCodes030.setCdVInquirySeq(seq);
        entityCodes030.setCreatedProgramId(dto.getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getLastUpdateTimestamp());

        serviceLifecycle.requestM00Codes030Service().register(entityCodes030);

        // Insert data Pme00ProjectInfo
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getCdV());
        entityInfo.setPeriod(dto.getPeriod());
        entityInfo.setStartDate(dto.getStartDate());
        entityInfo.setEndDate(dto.getEndDate());
        entityInfo.setKoreaPm(dto.getKoreaPm());
        entityInfo.setVietnamPl(dto.getVietNamPl());
        entityInfo.setStatus(dto.getStatus());
        entityInfo.setFramework(dto.getFramework());

        serviceLifecycle.requestPme00ProjectInfoService().register(entityInfo);

        // Insert data Pme00Member
        Pme00Member entityMember;
        for (Pme00Member member : dto.getLstMember()) {
            entityMember = new Pme00Member();
            entityMember.setCdVId(member.getCdVId());
            entityMember.setEmpId(member.getEmpId());
            entityMember.setEmpName(member.getEmpName());

            serviceLifecycle.requestPme00MemberService().register(entityMember);
        }

        return dto;
    }

    /**
     * Modify project info
     *
     * @param serviceLifecycle
     * @param dto
     * @return
     */
    public Pme00ProjectListDto modifyProject(ServiceLifecycle serviceLifecycle, Pme00ProjectListDto dto){

        // Update data M00Codes030
        List<M00Codes030> lstCodes030 = new ArrayList<>();
        M00Codes030 entityCodes030 = new M00Codes030();

        entityCodes030.setCdTpId(dto.getCdTpId());
        entityCodes030.setCategoryGroupId(dto.getCategoryGroupId());
        entityCodes030.setCdV(dto.getCdV());
        entityCodes030.setCdvMeaning(dto.getCdvMeaning());
        entityCodes030.setCdVExplain(dto.getCdVExplain());
        entityCodes030.setCdVInquirySeq(dto.getCdVInquirySeq());
        entityCodes030.setCreatedProgramId(dto.getCreatedProgramId());
        entityCodes030.setCreationTimestamp(dto.getCreationTimestamp());
        entityCodes030.setLastUpdateProgramId(dto.getLastUpdateProgramId());
        entityCodes030.setLastUpdateTimestamp(dto.getLastUpdateTimestamp());
        lstCodes030.add(entityCodes030);

        serviceLifecycle.requestM00Codes030Service().modify(lstCodes030);

        // Update data Pme00ProjectInfo
        List<Pme00ProjectInfo> lstProjectInfo = new ArrayList<>();
        Pme00ProjectInfo entityInfo = new Pme00ProjectInfo();
        entityInfo.setCdV(dto.getCdV());
        entityInfo.setPeriod(dto.getPeriod());
        entityInfo.setStartDate(dto.getStartDate());
        entityInfo.setEndDate(dto.getEndDate());
        entityInfo.setKoreaPm(dto.getKoreaPm());
        entityInfo.setVietnamPl(dto.getVietNamPl());
        entityInfo.setStatus(dto.getStatus());
        entityInfo.setFramework(dto.getFramework());
        lstProjectInfo.add(entityInfo);

        serviceLifecycle.requestPme00ProjectInfoService().modify(lstProjectInfo);

        // Update data Pme00Member
        // Delete old member
        serviceLifecycle.requestPme00MemberService().deleteMemberById(dto.getCdV(), null);

        // Insert new Pme00Member
        Pme00Member entityMember;
        for (Pme00Member member : dto.getLstMember()) {
            entityMember = new Pme00Member();
            entityMember.setCdVId(member.getCdVId());
            entityMember.setEmpId(member.getEmpId());
            entityMember.setEmpName(member.getEmpName());

            serviceLifecycle.requestPme00MemberService().register(entityMember);
        }

        return dto;
    }
}
