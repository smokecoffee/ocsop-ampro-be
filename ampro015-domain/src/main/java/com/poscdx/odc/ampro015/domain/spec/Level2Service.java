package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.AssetDto;
import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.ItemCodeDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface Level2Service {

    List<ItemCodeDto> findItemCodeInfos(ServiceLifecycle serviceLifecycle, String codeType, String description);

    void modifyItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    void deleteItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    String renderQRcode(String token);

    void updateAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto assetInfoDto);

    public List<AssetInfoDto> findAssetList(ServiceLifecycle serviceLifecycle, String emplName, int status);

    void exportExcel(ServiceLifecycle serviceLifecycle, HttpServletResponse response, AssetSearch assetSearch) throws IOException;

}
