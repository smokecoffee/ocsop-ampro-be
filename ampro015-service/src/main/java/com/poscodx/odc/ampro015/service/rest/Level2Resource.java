package com.poscodx.odc.ampro015.service.rest;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscoict.base.share.util.json.JsonUtil;
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

    /**
     * 
     * @return
     */
    @GetMapping("/render-qrcode")
    public String renderQRcode() {
        return this.serviceLifecycle.requestLevel2Service().renderQRcode("KHUGNSH6CCDS");
    }

    /**
     *
     * @param assetInfoDto
     */
    @PutMapping(path = "/asset")
    public void updateAsset(@RequestBody AssetInfoDto assetInfoDto) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] assetInfoDto -> " +JsonUtil.toJson(assetInfoDto), this);
        this.serviceLifecycle.requestLevel2Service().updateAsset(serviceLifecycle, assetInfoDto);
    }

    /**
     *
     * @param assetSearch
     * @return
     */
    @PostMapping("/asset/search")
    public List<AssetInfoDto> findAssetList(@RequestBody AssetSearch assetSearch) {
        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset -> " + assetSearch, this);
        String owner = assetSearch.getOwner();
        int status = assetSearch.getStatus();
        return this.serviceLifecycle.requestLevel2Service().findAssetList(serviceLifecycle, owner, status);
    }

    /**
     *
     * @param response
     * @param owner
     * @param status
     * @throws IOException
     */
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

        PosLogger.developerLog(PosLogWriterIF.INFO, "Asset Export Excel QR-CODE_" + currentDateTime+ ".xlsx", this);

        this.serviceLifecycle.requestLevel2Service().exportExcel(serviceLifecycle, response, assetSearch);
    }

}
