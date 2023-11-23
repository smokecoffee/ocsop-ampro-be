package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;

import java.util.List;

public interface AssetService {

    List<Asset> findAssetInfos(String emplName, int status);
    Asset retrieve(String id);
}
