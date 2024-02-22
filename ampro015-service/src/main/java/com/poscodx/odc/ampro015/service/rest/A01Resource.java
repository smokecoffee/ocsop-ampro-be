package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/asset")
public class A01Resource {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(A01Resource.class);
    private final ServiceLifecycle serviceLifecycle;

    @GetMapping(path = "/view-asset/")
    public AssetInfoDto getAsset(@RequestParam(value = "token", required = true) String token) {

        logger.info("[찾다] Asset Token : {}", token);
        return this.serviceLifecycle.requestLevel2QrCodeService().getAsset(serviceLifecycle, token);
    }

    /**
     * createAsset
     * @author 202293 - Trieu Le
     * @since 2023-11-23
     *
     * @param request AssetInfoDto
     * @return
     */
    @PostMapping("/asset")
    @PreAuthorize("hasAnyAuthority('ADD_ASSET')")
    public ResponseEntity<?> createAsset(@RequestBody AssetInfoDto request) {
        logger.info("[amp] create Asset -> {}", request);
        ResponseEntity<?> response = null;
        try {
            response = this.serviceLifecycle.requestLevel2QrCodeService().createAsset(serviceLifecycle, request);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    /**
     *
     * @param assetInfoDto
     */
    @PutMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('UPDATE_ASSET, UPDATE_ASSET_OWNER')")
    public void updateAsset(@RequestBody AssetInfoDto assetInfoDto) {
        logger.info( "[삭제] assetInfoDto ->  {}", assetInfoDto);
        this.serviceLifecycle.requestLevel2QrCodeService().updateAsset(serviceLifecycle, assetInfoDto);
    }

    @DeleteMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('DELETE_ASSET, DELETE_ASSET_OWNER')")
    public void deleteAsset(@RequestParam(value = "token", required = true) String token, @RequestParam(value = "userId", required = true) String userId) {
        logger.info("[삭제] Asset Token : {}",token);
        this.serviceLifecycle.requestLevel2QrCodeService().deleteAsset(serviceLifecycle, token, userId);
    }

    /**
     *
     * @param assetSearch
     * @return
     */
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('GET_ASSET, GET_ASSET_OWNER')")
    public List<AssetInfoDto> findAssetList(@RequestBody AssetSearch assetSearch) {
        logger.info("Asset -> {}", assetSearch);
        int assetID = assetSearch.getAssetId();
        String empId = assetSearch.getEmpId();
        int status = assetSearch.getStatus();
        return this.serviceLifecycle.requestLevel2QrCodeService().findAssetList(serviceLifecycle, assetID, empId, status);
    }

    /**
     *
     * @param response
     * @param owner
     * @param status
     * @throws IOException
     */
    @GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response,
                              @RequestParam(required = true) String owner,
                              @RequestParam(required = true) Integer status) throws IOException{
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=QR-CODE_" + currentDateTime+ ".xlsx";
        response.setHeader(headerKey,headerValue);
        AssetSearch assetSearch = new AssetSearch();
        assetSearch.setEmpId(owner);
        assetSearch.setStatus(status);

        logger.info("Asset Export Excel QR-CODE_{}", currentDateTime+".xlsx");

        this.serviceLifecycle.requestLevel2QrCodeService().exportExcel(serviceLifecycle, response, assetSearch);
    }

    /**
     *
     * @param response
     * @param token
     * @throws IOException
     */
    @GetMapping("/export-QRCode")
    public ResponseEntity<byte[]> exportToExcel1(HttpServletResponse response, @RequestParam(value = "token", required = true) String token) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = dateFormat.format(new Date());
        String filename = "QRCode-" + currentDate + ".png";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, no-cache");

        byte[] imageData = this.serviceLifecycle.requestLevel2QrCodeService().exportQRCode(serviceLifecycle, response, token);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @PostMapping(path = "/uploadImage/Assets/{folderName}")
    public String uploadFile(@PathVariable("folderName") String folderAssetName,
                             @RequestParam("file") MultipartFile image) {
        // http://localhost:9720/asset/uploadImage/Assets/assetId
        return this.serviceLifecycle.requestLevel2QrCodeService().uploadFile(folderAssetName, image);
    }
}
