package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;

import java.util.List;

public interface M00Codes030Service {

    List<M00Codes030> findAll();

    M00Codes030 find(M00Codes030Id id);

    void modify(List<M00Codes030> entityList);

    M00Codes030 register(M00Codes030 entity);

    void remove(M00Codes030Id id);

    int getMaxSeqInquiry(int cdTpId, int cateGroupId);

    List<M00Codes030> findM00Codes030(String cdV, String meaning);

    List<M00Codes030> findM00Codes030ById(int cdTpId);

}
