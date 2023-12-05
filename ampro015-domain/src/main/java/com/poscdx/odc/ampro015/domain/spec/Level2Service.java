package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectListDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface Level2Service {

    String renderQRcode(String token);

    void updateAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto assetInfoDto);

    public List<AssetInfoDto> findAssetList(ServiceLifecycle serviceLifecycle, String emplName, int status);

    void exportExcel(ServiceLifecycle serviceLifecycle, HttpServletResponse response, AssetSearch assetSearch) throws IOException;

    AssetInfoDto getAsset(ServiceLifecycle serviceLifecycle, String token);

    void deleteAsset(ServiceLifecycle serviceLifecycle, String token, int userId);

    ResponseEntity<?> createAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto request);

}
