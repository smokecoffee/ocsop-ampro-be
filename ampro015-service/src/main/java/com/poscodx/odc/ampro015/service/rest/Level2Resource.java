package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.AssetDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.ItemCodeDto;
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
    @PostMapping("/asset/search")
    public List<AssetDto> findAssetList(@RequestBody AssetSearch assetSearch) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset -> " + assetSearch, this);
        String owner = assetSearch.getOwner();
        int status = assetSearch.getStatus();
        return this.serviceLifecycle.requestLevel2Service().findAssetList(serviceLifecycle, owner, status);
    }

    @GetMapping("/render-qrcode")
    public String RenderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().RenderQRcode("KHUGNSH6CCDS");
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
