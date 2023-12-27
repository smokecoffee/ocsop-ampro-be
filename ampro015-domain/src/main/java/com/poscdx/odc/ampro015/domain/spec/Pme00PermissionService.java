package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Permission;

import java.util.List;

public interface Pme00PermissionService {

    public Pme00Permission find(int id);

    public List<Pme00Permission> findAll();

    public void modify(List<Pme00Permission> entityList);

    public Pme00Permission register(Pme00Permission entity);

    public void remove(int id);
}
