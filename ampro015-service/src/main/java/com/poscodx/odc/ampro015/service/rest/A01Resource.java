package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscoict.base.share.util.json.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asset")
public class A01Resource {

    private final ServiceLifecycle serviceLifecycle;

    @CrossOrigin
    @GetMapping(path = "/view-asset/")
    public AssetInfoDto getAsset(@RequestParam(value = "token", required = true) String token) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[찾다] Asset Token : " +JsonUtil.toJson(token), this);
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
    @CrossOrigin
    @PostMapping("/asset")
    public ResponseEntity<?> createAsset(@RequestBody AssetInfoDto request) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[amp] create Asset -> " +JsonUtil.toJson(request), this);
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
    @CrossOrigin
    @PutMapping(path = "/")
    public void updateAsset(@RequestBody AssetInfoDto assetInfoDto) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] assetInfoDto -> " +JsonUtil.toJson(assetInfoDto), this);
        this.serviceLifecycle.requestLevel2QrCodeService().updateAsset(serviceLifecycle, assetInfoDto);
    }

    @CrossOrigin
    @DeleteMapping(path = "/")
    public void deleteAsset(@RequestParam(value = "token", required = true) String token, @RequestParam(value = "userId", required = true) String userId) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] Asset Token : " +JsonUtil.toJson(token), this);
        this.serviceLifecycle.requestLevel2QrCodeService().deleteAsset(serviceLifecycle, token, userId);
    }

    /**
     *
     * @param assetSearch
     * @return
     */
    @CrossOrigin
    @PostMapping("/search")
    public List<AssetInfoDto> findAssetList(@RequestBody AssetSearch assetSearch) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset -> " + assetSearch, this);
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
    @CrossOrigin
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

        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset Export Excel QR-CODE_" + currentDateTime+ ".xlsx", this);

        this.serviceLifecycle.requestLevel2QrCodeService().exportExcel(serviceLifecycle, response, assetSearch);
    }

    /**
     *
     * @param response
     * @param token
     * @throws IOException
     */
    @CrossOrigin
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

    @CrossOrigin
    @PostMapping(path = "/uploadImage/Assets/{folderName}")
    public String uploadFile(@PathVariable("folderName") String folderAssetName,
                             @RequestParam("file") MultipartFile image) {
        // http://localhost:9720/asset/uploadImage/Assets/assetId
        return this.serviceLifecycle.requestLevel2QrCodeService().uploadFile(folderAssetName, image);
    }
}
