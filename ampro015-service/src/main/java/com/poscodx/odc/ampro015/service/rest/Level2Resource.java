package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscoict.base.share.util.json.JsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    private final ServiceLifecycle serviceLifecycle;

    @GetMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeType", value = "코드구분", example = "A: Q코드 / B: S코드 / C : QR코드 / null : 전체"),
            @ApiImplicitParam(name = "description", value = "검색어", example = "Shaped Refractory")
    })
    public List<ItemCodeDto> findItemCodeInfos(@RequestParam(value = "codeType", required = false) String codeType,
                                               @RequestParam(value = "description", required = false) String description) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "codeType -> " + codeType, this);
        PosLogger.developerLog(PosLogWriterIF.INFO, "description -> " + description, this);
        return this.serviceLifecycle.requestLevel2Service().findItemCodeInfos(serviceLifecycle, codeType, description);
    }

    @PutMapping("/modify")
    public void modifyItemCodeInfo(@RequestBody List<ItemCodeDto> itemCodeDtoList) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[수정] itemCodeDtoList -> " + JsonUtil.toJson(itemCodeDtoList), this);
        this.serviceLifecycle.requestLevel2Service().modifyItemCodeInfo(serviceLifecycle, itemCodeDtoList);
    }

    @PostMapping("/delete")
    public void deleteItemCodeInfo(@RequestBody List<ItemCodeDto> itemCodeDtoList) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] itemCodeDtoList -> " + JsonUtil.toJson(itemCodeDtoList), this);
        this.serviceLifecycle.requestLevel2Service().deleteItemCodeInfo(serviceLifecycle, itemCodeDtoList);
    }

    @GetMapping("/render-qrcode")
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }

    @PutMapping(path = "/asset")
    public void updateAsset(@RequestBody AssetInfoDto assetInfoDto) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] assetInfoDto -> " +JsonUtil.toJson(assetInfoDto), this);
        this.serviceLifecycle.requestLevel2Service().updateAsset(serviceLifecycle, assetInfoDto);
    }

    @PostMapping("/asset/search")
    public List<AssetInfoDto> findAssetList(@RequestBody AssetSearch assetSearch) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset -> " + assetSearch, this);
        String owner = assetSearch.getOwner();
        int status = assetSearch.getStatus();
        return this.serviceLifecycle.requestLevel2Service().findAssetList(serviceLifecycle, owner, status);
    }

    @GetMapping("/asset/export-excel")
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
        assetSearch.setOwner(owner);
        assetSearch.setStatus(status);
        this.serviceLifecycle.requestLevel2Service().exportExcel(serviceLifecycle, response, assetSearch);
    }
}
