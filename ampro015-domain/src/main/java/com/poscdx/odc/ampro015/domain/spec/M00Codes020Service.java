package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;

import java.util.List;

public interface M00Codes020Service {

    List<M00Codes020> findAll();

    M00Codes020 find(int cdTpId);

}
