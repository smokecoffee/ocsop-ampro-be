package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Level2Service {

    List<ItemCodeDto> findItemCodeInfos(ServiceLifecycle serviceLifecycle, String codeType, String description);

    void modifyItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    void deleteItemCodeInfo(ServiceLifecycle serviceLifecycle, List<ItemCodeDto> itemCodeDtoList);

    String RenderQRcode(String token);

    ResponseEntity<?> addNewAsset(AssetInfoDto request);
}
