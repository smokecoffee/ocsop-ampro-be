package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.ItemCodeDto;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Level2Service {

    List<ItemCodeDto> findItemCodeInfos(ServiceLifecycle serviceLifecycle, String codeType, String description);

    void modifyItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    void deleteItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    String renderQRcode(String token);

    void updateAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto assetInfoDto);

    ResponseEntity<?> createAsset(ServiceLifecycle serviceLifecycle, AssetInfoDto request);


}
