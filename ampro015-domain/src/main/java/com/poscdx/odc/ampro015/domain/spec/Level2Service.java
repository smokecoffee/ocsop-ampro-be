package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.AssetSearch;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.entity.Pme00MeetingResponse;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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

    Pme00MeetingResponse addMeeting(ServiceLifecycle serviceLifecycle, Pme00Meeting newMeeting);

    public Pme00MeetingResponse getInforBookingRoom(ServiceLifecycle serviceLifecycle, int id);
}
