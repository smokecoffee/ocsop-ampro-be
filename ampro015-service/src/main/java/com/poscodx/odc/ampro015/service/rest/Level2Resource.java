package com.poscodx.odc.ampro015.service.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.entity.ItemCodeDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.posco.reuse.common.logging.PosLogWriterIF;
import com.posco.reuse.common.logging.PosLogger;
import com.poscoict.base.share.util.json.JsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level2")
public class Level2Resource {

    private final ServiceLifecycle serviceLifecycle;
    @GetMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeType", value = "코드구분", example = "A: Q코드 / B: S코드 / C : QR코드 / null : 전체" ),
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

    @PutMapping(path = "/asset", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public String updateAsset(@RequestBody String updateInfo) {
        this.serviceLifecycle.requestLevel2Service().updateAsset(serviceLifecycle, updateInfo);
        return "OK";
    }
}
