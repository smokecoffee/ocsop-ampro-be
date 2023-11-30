package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;

import java.util.List;

public interface M00Codes020Store {

    List<M00Codes020> retrieveAll();

    M00Codes020 retrieve(int cdTpId);
}
