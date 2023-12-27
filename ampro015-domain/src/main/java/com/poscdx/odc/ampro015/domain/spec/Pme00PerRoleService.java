package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00PerRole;

import java.util.List;

public interface Pme00PerRoleService {

    public Pme00PerRole find(int id);

    public List<Pme00PerRole> findAll();

    public void modify(List<Pme00PerRole> entityList);

    public Pme00PerRole register(Pme00PerRole entity);

    public void remove(int id);
}
