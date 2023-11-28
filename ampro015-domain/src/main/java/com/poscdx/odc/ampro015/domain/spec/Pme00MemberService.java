package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;

import java.util.List;

public interface Pme00MemberService {

    Pme00Member find(int id);

    List<Pme00Member> findAll();

    void modify(List<Pme00Member> entityList);

    Pme00Member register(Pme00Member entity);

    void remove(int id);

}
