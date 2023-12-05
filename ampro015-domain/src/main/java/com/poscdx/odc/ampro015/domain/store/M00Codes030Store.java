package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;

import java.util.List;

public interface M00Codes030Store {

    List<M00Codes030> retrieveAll();

    M00Codes030 retrieve(M00Codes030Id id);

    M00Codes030 update(M00Codes030 entity);

    M00Codes030 create(M00Codes030 entity);

    void delete(M00Codes030Id id);

    int getMaxSeqInquiry(int cdTpId, int cateGroupId);
}
