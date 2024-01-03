package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;

import java.util.List;

public interface Pme00MemberStore {
    Pme00Member retrieve(int id);

    List<Pme00Member> retrieveAll();

    Pme00Member update(Pme00Member pme00Member);

    Pme00Member create(Pme00Member entity);

    void delete(int id);

    List<Pme00Member> getListMemberByCdVId(String cdVId);

    void deleteMemberById(String cdVId, String empId);

}
